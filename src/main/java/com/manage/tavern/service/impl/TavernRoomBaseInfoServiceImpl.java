package com.manage.tavern.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.tavern.mapper.TavernRoomBaseInfoMapper;
import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.form.RoomBaseInfoForm;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.RoomBaseInfoVo;
import com.manage.tavern.po.TavernRoomBaseInfo;
import com.manage.tavern.po.sys.UserInfoModel;
import com.manage.tavern.service.TavernAuditResultService;
import com.manage.tavern.service.TavernRoomBaseInfoService;
import com.manage.tavern.utils.BeanCopierUtils;
import com.manage.tavern.utils.DateUtils;
import com.manage.tavern.utils.TokenUtil;
import com.manage.tavern.utils.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/21 19:52
 * @version:
 * @modified By:
 */
@Service
public class TavernRoomBaseInfoServiceImpl implements TavernRoomBaseInfoService {

    @Resource
    private TavernRoomBaseInfoMapper tavernRoomBaseInfoMapper;

    @Resource
    private TavernAuditResultService tavernAuditResultService;

    @Override
    public PaginationModel<RoomBaseInfoVo> getList(RoomAuditQuery query) {
        UserInfoModel user = TokenUtil.getUser();
        if (!user.getRoleIds().contains("ROLE_TAVERN_ROOT") && StringUtils.isBlank(query.getTgfd())) {
            query.setTgfd(user.getUserName());
        }
        PaginationModel<RoomBaseInfoVo> res = new PaginationModel<>();
        QueryWrapper<TavernRoomBaseInfo> qw = new QueryWrapper<>();
        qw.lambda().eq(TavernRoomBaseInfo::getStatus,1);
        if (StringUtils.isNotBlank(query.getTgfd())) {
            qw.lambda().eq(TavernRoomBaseInfo::getTgfd,query.getTgfd());
        }
        if (StringUtils.isNotBlank(query.getArea())) {
            qw.lambda().eq(TavernRoomBaseInfo::getArea,query.getArea());
        }
        if (StringUtils.isNotBlank(query.getRoomName())) {
            qw.lambda().like(TavernRoomBaseInfo::getRoomName,query.getRoomName());
        }
        qw.lambda().orderByDesc(TavernRoomBaseInfo::getCreateTime);
        IPage<TavernRoomBaseInfo> infoIPage = new Page<>(query.getCurrentPageNo(),query.getPageSize());
        IPage<TavernRoomBaseInfo> page = tavernRoomBaseInfoMapper.selectPage(infoIPage, qw);
        List<TavernRoomBaseInfo> records = page.getRecords();
        List<RoomBaseInfoVo> roomBaseInfoVos = BeanCopierUtils.convertList(records, RoomBaseInfoVo.class);
        res.setData(roomBaseInfoVos);
        res.setCode("0");
        res.setCount(page.getTotal());
        res.setPageIndex(query.getCurrentPageNo());
        res.setPageSize(query.getPageSize());
        return res;
    }

    @Override
    public void edit(RoomBaseInfoForm form) {
        TavernRoomBaseInfo convert = BeanCopierUtils.convert(form, TavernRoomBaseInfo.class);
        if (Objects.equals(form.getUpdateFlag(),"edit")) {
            // 修改
            tavernRoomBaseInfoMapper.updateById(convert);
        }else if (Objects.equals(form.getUpdateFlag(),"add")) {
            // 新增
            convert.setStatus(1);
            tavernRoomBaseInfoMapper.insert(convert);
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    ServletRequestAttributes servletRequestAttributes =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//                    HttpServletRequest req = servletRequestAttributes.getRequest();
//                    UserUtils.setRequest(req);
//                    // 自动更新可编辑列表的数据
//                    tavernAuditResultService.insertResult(DateUtils.getCurrDate("yyyyMM"),"SYSTEM");
//                }
//            }).start();

        }else {
            throw new RuntimeException("编辑失败:约定外的操作类型!");
        }

    }

    @Override
    public void delRoomById(Integer id) {
        TavernRoomBaseInfo info = new TavernRoomBaseInfo();
        info.setId(id);
        info.setStatus(0);
        tavernRoomBaseInfoMapper.updateById(info);
    }
}
