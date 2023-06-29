package com.manage.tavern.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.tavern.constant.CommonConstant;
import com.manage.tavern.constant.TgfdCommissionRules;
import com.manage.tavern.mapper.ByBjDataMapper;
import com.manage.tavern.mapper.RoomAuditMapper;
import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.base.SelectData;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.RoomAuditInfo;
import com.manage.tavern.po.sys.UserInfoModel;
import com.manage.tavern.service.RoomAuditService;
import com.manage.tavern.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/7 19:45
 * @version:
 * @modified By:
 */
@Service
@Slf4j
public class RoomAuditServiceImpl implements RoomAuditService {

    @Resource
    private RoomAuditMapper roomAuditMapper;

    @Resource
    private ByBjDataMapper byBjDataMapper;

    @Override
    public PaginationModel<RoomAuditInfo> getList(RoomAuditQuery query) {
        UserInfoModel user = null;
        try {
            user = TokenUtil.getUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (Objects.equals(query.getFlag(),1)) {
            user = new UserInfoModel();
            user.setUserId(100200);
            user.setUserName("管理员");
            user.setLoginName("root");
            user.setRoleIds(Arrays.asList("ROLE_TAVERN_ROOT"));
        }
        if (!user.getRoleIds().contains("ROLE_TAVERN_ROOT") && StringUtils.isBlank(query.getTgfd())) {
            query.setTgfd(user.getUserName());
        }
        PaginationModel<RoomAuditInfo> res = new PaginationModel<>();
        IPage<RoomAuditInfo> page = new Page<>(query.getCurrentPageNo(), query.getPageSize());
        List<RoomAuditInfo> list = roomAuditMapper.getList(page, query);
        for (RoomAuditInfo auditInfo : list) {
            log.info(".............roomName:{}", auditInfo.getRoomName());
            // 获取日常开支(财务报销里的金额求和)
            String dailyExpend = roomAuditMapper.getDailyExpend(auditInfo.getRoomName(), query);
            String otherDailyExpend = roomAuditMapper.getOtherDailyExpend(auditInfo.getRoomName(), query);
            BigDecimal b1 = new BigDecimal(StringUtils.isBlank(dailyExpend) ? "0" : dailyExpend.replace(",", ""));
            BigDecimal b2 = new BigDecimal(StringUtils.isBlank(otherDailyExpend) ? "0" : otherDailyExpend.replace(",", ""));
            // 获取客耗品
            String khFee = getKeFee(auditInfo);
            // 计算布草开支
            String bcExpend = getBcExpend(auditInfo, query.getMonth());
            // 获取备注
            String remark = getRemark(auditInfo);
            // 获取保洁费用
            String bjFee = roomAuditMapper.getBjFee(auditInfo.getRoomName(), query);
            auditInfo.setBjFee(bjFee);
            auditInfo.setDailyExpend(b1.add(b2).toString());
            auditInfo.setKhFee(khFee);
            auditInfo.setBcExpend(bcExpend);
            auditInfo.setRemark(remark);
            // 计算开支总计
            String sumExpend = getSumExpend(auditInfo);
            auditInfo.setSumExpend(sumExpend);
            // 计算净利润
            String netProfits = getNetProfits(auditInfo);
            auditInfo.setNetProfits(netProfits);
            // 获取房屋升级
            String fwsj = roomAuditMapper.getFwsjFee(auditInfo.getRoomName(), query);
            // 计算佣金
            String commission = getCommission(auditInfo);
            auditInfo.setCommission(commission);
            // 计算结算金额
            String settlementAmount = getSettlementAmount(auditInfo, fwsj);
            auditInfo.setSettlementAmount(settlementAmount);
        }
        res.setData(list);
        res.setCode("0");
        res.setCount(page.getTotal());
        res.setPageIndex(query.getCurrentPageNo());
        res.setPageSize(query.getPageSize());
        return res;
    }

    /**
     * 计算佣金
     *
     * @param auditInfo
     * @return
     */
    private String getCommission(RoomAuditInfo auditInfo) {
        String res = "";
        String tgfd = auditInfo.getTgfd();
        // 平台打款
        BigDecimal priceClean = new BigDecimal(StringUtils.isBlank(auditInfo.getPriceClean()) ? "0" : auditInfo.getPriceClean().replace("￥", "").replace(",", "").trim());
        // 保洁
        BigDecimal bjFee = new BigDecimal(StringUtils.isBlank(auditInfo.getBjFee()) ? "0" : auditInfo.getBjFee().replace("￥", "").replace(",", "").trim());
        // 客耗
        BigDecimal khFee = new BigDecimal(StringUtils.isBlank(auditInfo.getKhFee()) ? "0" : auditInfo.getKhFee().replace("￥", "").replace(",", "").trim());
        // 布草
        BigDecimal bcExpend = new BigDecimal(StringUtils.isBlank(auditInfo.getBcExpend()) ? "0" : auditInfo.getBcExpend().replace("￥", "").replace(",", "").trim());
        if (Objects.equals(tgfd, TgfdCommissionRules.TEACHER_ZHANG.getTgfdName())) {
            BigDecimal subtract = priceClean.subtract(khFee).subtract(bjFee).subtract(bcExpend);
            res = subtract.multiply(TgfdCommissionRules.TEACHER_ZHANG.getFactor()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            return res;
        }
        if (Objects.equals(tgfd, TgfdCommissionRules.CL.getTgfdName())) {
            res = priceClean.multiply(TgfdCommissionRules.CL.getFactor()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            return res;
        }
        if (Objects.equals(tgfd, TgfdCommissionRules.SMGC.getTgfdName())) {
            res = priceClean.multiply(TgfdCommissionRules.SMGC.getFactor()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            return res;
        }
        if (Objects.equals(auditInfo.getRoomName(), "星悦城23-1-101")) {
            res = priceClean.multiply(TgfdCommissionRules.JJK.getFactor()).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
            return res;
        }
        return null;
    }

    @Override
    public List<SelectData> getSelectData(String name, Integer type) {
        UserInfoModel user = TokenUtil.getUser();
        log.info("getSelectData......user:{}", user.toString());
        if (!user.getRoleIds().contains("ROLE_TAVERN_ROOT") && StringUtils.isBlank(name)) {
            log.info("getSelectData......{}", user.toString());
            name = user.getUserName();
        }
        List<SelectData> res = roomAuditMapper.getSelectData(name);
        return res;
    }

    private String getKeFee(RoomAuditInfo auditInfo) {
        String roomType = auditInfo.getRoomType();
        String rzDays = auditInfo.getRzDays();
        String tempNum = "";
        if (Objects.equals(roomType, "1房")) {
            tempNum = "3";
        } else if (Objects.equals(roomType, "2房")) {
            tempNum = "5";
        } else if (Objects.equals(roomType, "3房")) {
            tempNum = "7";
        } else if (Objects.equals(roomType, "4房")) {
            tempNum = "9";
        }
        if (StringUtils.isBlank(rzDays) || StringUtils.isBlank(tempNum)) {
            return "";
        }
        BigDecimal b1 = new BigDecimal(tempNum);
        BigDecimal b2 = new BigDecimal(rzDays);
        return b1.multiply(b2).toString();
    }

    /**
     * 计算结算金额
     * 公式=净利润÷2+房租-房屋升级
     *
     * @param auditInfo
     * @return
     */
    private String getSettlementAmount(RoomAuditInfo auditInfo, String fwsj) {
        String res = "";
        String tgfd = auditInfo.getTgfd();
        // 房屋升级
        BigDecimal bfwsj = new BigDecimal(StringUtils.isBlank(fwsj) ? "0" : fwsj.replace("￥", "").replace(",", "").trim());
        // 净利润
        BigDecimal netProfits = new BigDecimal(StringUtils.isBlank(auditInfo.getNetProfits()) ? "0" : auditInfo.getNetProfits().replace("￥", "").replace(",", "").trim());
        // 房租
        BigDecimal actualZj = new BigDecimal(StringUtils.isBlank(auditInfo.getActualZj()) ? "0" : auditInfo.getActualZj().replace("￥", "").replace(",", "").trim());
        // 平台打款
        BigDecimal priceClean = new BigDecimal(StringUtils.isBlank(auditInfo.getPriceClean()) ? "0" : auditInfo.getPriceClean().replace("￥", "").replace(",", "").trim());
        // 开支总计
        BigDecimal sumExpend = new BigDecimal(StringUtils.isBlank(auditInfo.getSumExpend()) ? "0" : auditInfo.getSumExpend().replace("￥", "").replace(",", "").trim());
        // 佣金
        BigDecimal commission = new BigDecimal(StringUtils.isBlank(auditInfo.getCommission()) ? "0" : auditInfo.getCommission().replace("￥", "").replace(",", "").trim());
        if (Objects.equals(TgfdCommissionRules.JJK.getTgfdName(), tgfd)) {
            if (Objects.equals("星悦城23-1-101", auditInfo.getRoomName())) {
                // 金坚坤星悦城23-1-101=平台打款-开支总计-佣金（房租默认0）
                return priceClean.subtract(sumExpend).subtract(commission).toString();
            } else {
                // 金坚坤（除了星悦城23-1-101）结算金额公式=净利润*60%+房租
                return netProfits.multiply(
                        new BigDecimal("0.6")).setScale(2, BigDecimal.ROUND_HALF_UP)
                        .add(actualZj).toString();
            }
        }
        if (Objects.equals("凯旋银行大厦2801", auditInfo.getRoomName())) {
            // 重庆凯旋银行大厦2801=净利润*30%+房租
            return netProfits.multiply(
                    new BigDecimal("0.3")).setScale(2, BigDecimal.ROUND_HALF_UP)
                    .add(actualZj).toString();
        }
        if (Objects.equals("马建华", auditInfo.getTgfd())) {
            // 马建华=净利润*40%+房租
            return netProfits.multiply(
                    new BigDecimal("0.4")).setScale(2, BigDecimal.ROUND_HALF_UP)
                    .add(actualZj).toString();
        }
        if (CommonConstant.SETTLEMENTAMOUNT_OTHER_LIST.contains(auditInfo.getTgfd())
                || CommonConstant.SETTLEMENTAMOUNT_OTHER_LIST.contains(auditInfo.getRoomName())) {
            return priceClean.subtract(sumExpend).subtract(commission).toString();
        }
        BigDecimal add = netProfits.divide(new BigDecimal(2), 2).add(actualZj).subtract(bfwsj);
        return add.toString();
    }

    /**
     * 计算净利润
     *
     * @param auditInfo
     * @return
     */
    private String getNetProfits(RoomAuditInfo auditInfo) {
        if (CommonConstant.NET_PRO_FITS_ZERO_LIST.contains(auditInfo.getTgfd())
                || CommonConstant.NET_PRO_FITS_ZERO_LIST.contains(auditInfo.getRoomName())) {
            return "0";
        }
        BigDecimal sumExpend = new BigDecimal(StringUtils.isBlank(auditInfo.getSumExpend()) ? "0" : auditInfo.getSumExpend().replace("￥", "").replace(",", "").trim());
        BigDecimal priceClean = new BigDecimal(StringUtils.isBlank(auditInfo.getPriceClean()) ? "0" : auditInfo.getPriceClean().replace("￥", "").replace(",", "").trim());
        return priceClean.subtract(sumExpend).toString();
    }

    /**
     * 计算开支总计
     *
     * @param auditInfo
     * @return
     */
    private String getSumExpend(RoomAuditInfo auditInfo) {
        BigDecimal actualZj = new BigDecimal(StringUtils.isBlank(auditInfo.getActualZj()) ? "0" : auditInfo.getActualZj().replace("￥", "").replace(",", "").trim());
        BigDecimal khFee = new BigDecimal(StringUtils.isBlank(auditInfo.getKhFee()) ? "0" : auditInfo.getKhFee().replace("￥", "").replace(",", "").trim());
        BigDecimal bjFee = new BigDecimal(StringUtils.isBlank(auditInfo.getBjFee()) ? "0" : auditInfo.getBjFee().replace("￥", "").replace(",", "").trim());
        BigDecimal dailyExpend = new BigDecimal(StringUtils.isBlank(auditInfo.getDailyExpend()) ? "0" : auditInfo.getDailyExpend().replace("￥", "").replace(",", "").trim());
        BigDecimal bcExpend = new BigDecimal(StringUtils.isBlank(auditInfo.getBcExpend()) ? "0" : auditInfo.getBcExpend().replace("￥", "").replace(",", "").trim());
        String sum = actualZj.add(khFee).add(bjFee).add(dailyExpend).add(bcExpend).toString();
        return sum;
    }

    /**
     * 计算布草开支
     *
     * @param auditInfo
     * @return
     */
    private String getBcExpend(RoomAuditInfo auditInfo, String month) {
        String roomType = auditInfo.getTypeLevel();
        if (StringUtils.isBlank(roomType)) {
            return "";
        }
        // 获取当月保洁次数
        Integer bjCounts = byBjDataMapper.getBjCounts(auditInfo, month);
        BigDecimal bcSum = CommonConstant.BC_PRICE_SUM.getBcSum(Integer.parseInt(roomType));
        if (Objects.isNull(bjCounts) || Objects.isNull(bcSum)) {
            return "";
        }
        return new BigDecimal(bjCounts).multiply(bcSum).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 获取备注
     *
     * @param auditInfo
     * @return
     */
    private String getRemark(RoomAuditInfo auditInfo) {
        String remark = "客耗品%s元/天";
        String roomType = auditInfo.getRoomType();
        String tempNum = "";
        if (Objects.equals(roomType, "1房")) {
            tempNum = "3";
        } else if (Objects.equals(roomType, "2房")) {
            tempNum = "5";
        } else if (Objects.equals(roomType, "3房")) {
            tempNum = "7";
        } else if (Objects.equals(roomType, "4房")) {
            tempNum = "9";
        } else {
            return "";
        }
        return String.format(remark, tempNum);
    }
}
