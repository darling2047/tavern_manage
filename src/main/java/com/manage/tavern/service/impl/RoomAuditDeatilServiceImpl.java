package com.manage.tavern.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.tavern.mapper.ByBjDataMapper;
import com.manage.tavern.mapper.TavernBydataRoomMapper;
import com.manage.tavern.model.base.PaginationModel;
import com.manage.tavern.model.query.RoomAuditQuery;
import com.manage.tavern.model.vo.TavernByBjDataVo;
import com.manage.tavern.model.vo.TavernByDataRoomVo;
import com.manage.tavern.model.vo.TavernDingBxDataVo;
import com.manage.tavern.po.sys.UserInfoModel;
import com.manage.tavern.service.RoomAuditDeatilService;
import com.manage.tavern.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/16 17:46
 * @version:
 * @modified By:
 */
@Service
@Slf4j
public class RoomAuditDeatilServiceImpl implements RoomAuditDeatilService {

    @Resource
    private ByBjDataMapper byBjDataMapper;

    @Resource
    private TavernBydataRoomMapper tavernBydataRoomMapper;

    @Override
    public PaginationModel<TavernByBjDataVo> getBjDetails(RoomAuditQuery query) {
        PaginationModel<TavernByBjDataVo> res = new PaginationModel<>();
        UserInfoModel user = TokenUtil.getUser();
        if (!user.getRoleIds().contains("ROLE_TAVERN_ROOT") && StringUtils.isBlank(query.getTgfd())) {
            query.setTgfd(user.getUserName());
        }
        IPage<TavernByBjDataVo> iPage = new Page<>(query.getCurrentPageNo(),query.getPageSize());
        List<TavernByBjDataVo> list = byBjDataMapper.getBjDetails(iPage, query);
        res.setData(list);
        res.setCode("0");
        res.setCount(iPage.getTotal());
        res.setPageIndex(query.getCurrentPageNo());
        res.setPageSize(query.getPageSize());
        return res;
    }

    @Override
    public PaginationModel<TavernDingBxDataVo> getDingBxDetails(RoomAuditQuery query) {
        PaginationModel<TavernDingBxDataVo> res = new PaginationModel<>();
        UserInfoModel user = TokenUtil.getUser();
        if (!user.getRoleIds().contains("ROLE_TAVERN_ROOT") && StringUtils.isBlank(query.getTgfd())) {
            query.setTgfd(user.getUserName());
        }
        IPage<TavernDingBxDataVo> iPage = new Page<>(query.getCurrentPageNo(),query.getPageSize());
        List<TavernDingBxDataVo> list = byBjDataMapper.getDingBxDetails(iPage, query);
        for (TavernDingBxDataVo item : list) {
            String title = item.getTitle();
            int index = title.indexOf("提交");
            String creator = title.substring(0, index);
            item.setCreator(creator);
        }
        res.setData(list);
        res.setCode("0");
        res.setCount(iPage.getTotal());
        res.setPageIndex(query.getCurrentPageNo());
        res.setPageSize(query.getPageSize());
        return res;
    }

    @Override
    public PaginationModel<TavernByDataRoomVo> getBycwDetails(RoomAuditQuery query) {
        PaginationModel<TavernByDataRoomVo> res = new PaginationModel<>();
        UserInfoModel user = TokenUtil.getUser();
        if (!user.getRoleIds().contains("ROLE_TAVERN_ROOT") && StringUtils.isBlank(query.getTgfd())) {
            query.setTgfd(user.getUserName());
        }

        IPage<TavernByDataRoomVo> iPage = new Page<>(query.getCurrentPageNo(),query.getPageSize());
        List<TavernByDataRoomVo> list = tavernBydataRoomMapper.getBycwDetails(iPage, query);
        res.setData(list);
        res.setCode("0");
        res.setCount(iPage.getTotal());
        res.setPageIndex(query.getCurrentPageNo());
        res.setPageSize(query.getPageSize());
        return res;
    }
}
