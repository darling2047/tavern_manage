package com.manage.tavern.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.tavern.mapper.BkftDetailsLogMapper;
import com.manage.tavern.mapper.RoomBkftMapper;
import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.excel.RoomBkftExcelModel;
import com.manage.tavern.model.query.BkftRoomQuery;
import com.manage.tavern.model.vo.RoomBkftVo;
import com.manage.tavern.po.TavernBkftDetailsLog;
import com.manage.tavern.po.TavernRoomBkft;
import com.manage.tavern.po.sys.UserInfoModel;
import com.manage.tavern.service.RoomBkftService;
import com.manage.tavern.utils.BeanCopierUtils;
import com.manage.tavern.utils.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DLL
 * @since 2023-06-16
 */
@Service
public class RoomBkftServiceImpl extends ServiceImpl<RoomBkftMapper, TavernRoomBkft> implements RoomBkftService {

    @Resource
    RoomBkftMapper roomBkftMapper;

    @Resource
    BkftDetailsLogMapper bkftDetailsLogMapper;

    @Override
    public PaginationModel<RoomBkftVo> getList(BkftRoomQuery query) {
        UserInfoModel user = TokenUtil.getUser();
        if (!user.getRoleIds().contains("ROLE_TAVERN_ROOT") && StringUtils.isBlank(query.getTgfd())) {
            query.setTgfd(user.getUserName());
        }
        PaginationModel<RoomBkftVo> res = new PaginationModel<>();
        QueryWrapper<TavernRoomBkft> qw = getQw(query);
        IPage<TavernRoomBkft> iPage = new Page<>(query.getCurrentPageNo(),query.getPageSize());
        IPage<TavernRoomBkft> page = roomBkftMapper.selectPage(iPage, qw);
        List<TavernRoomBkft> records = page.getRecords();
        List<RoomBkftVo> list = BeanCopierUtils.convertList(records, RoomBkftVo.class);
        res.setData(list);
        res.setCode("0");
        res.setCount(page.getTotal());
        res.setPageIndex(query.getCurrentPageNo());
        res.setPageSize(query.getPageSize());
        return res;
    }

    @Override
    public void saveBkftFee(RoomBkftExcelModel item) {
        UserInfoModel user = TokenUtil.getUser();
        TavernRoomBkft bkft = BeanCopierUtils.convert(item,TavernRoomBkft.class);
        bkft.setCreator(user.getLoginName());
        roomBkftMapper.insert(bkft);
    }

    @Override
    public void saveExcelDetailLogs(ThreadLocal<Long> exportIdLocal, List<TavernBkftDetailsLog> logList) {
        logList.stream().forEach(item -> {
            item.setId(exportIdLocal.get());
            bkftDetailsLogMapper.insert(item);
        });
    }

    private QueryWrapper<TavernRoomBkft> getQw(BkftRoomQuery query) {
        QueryWrapper<TavernRoomBkft> qw = new QueryWrapper<>();
        if (StringUtils.isNotBlank(query.getTgfd())) {
            qw.lambda().eq(TavernRoomBkft::getTgfd,query.getTgfd());
        }

        if (StringUtils.isNotBlank(query.getRoomName())) {
            qw.lambda().eq(TavernRoomBkft::getRoomName,query.getRoomName());
        }

        if (StringUtils.isNotBlank(query.getArea())) {
            qw.lambda().eq(TavernRoomBkft::getArea,query.getArea());
        }

        if (StringUtils.isNotBlank(query.getMonth())) {
            qw.lambda().eq(TavernRoomBkft::getMonth,query.getMonth());
        }
        return qw;
    }
}
