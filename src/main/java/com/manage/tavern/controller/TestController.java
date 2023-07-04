package com.manage.tavern.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manage.tavern.mapper.TavernDingProDataMapper;
import com.manage.tavern.mapper.TavernDingSkApprovalMapper;
import com.manage.tavern.mapper.TavernDingSkTableDataMapper;
import com.manage.tavern.model.base.ResponResult;
import com.manage.tavern.model.form.ByDataRoomForm;
import com.manage.tavern.po.TavernDingFormApproval;
import com.manage.tavern.po.TavernDingProData;
import com.manage.tavern.po.TavernDingSkTableData;
import com.manage.tavern.service.DingTalkDataApiService;
import com.manage.tavern.service.SpiderHttpService;
import com.manage.tavern.service.TavernAuditResultService;
import com.manage.tavern.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/4/8 20:11
 * @version:
 * @modified By:
 */
@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {


    @Resource
    private SpiderHttpService spiderHttpService;

    @Resource
    private DingTalkDataApiService dingTalkDataApiService;

    @Resource
    private TavernDingProDataMapper tavernDingProDataMapper;

    @Resource
    private TavernDingSkApprovalMapper tavernDingSkApprovalMapper;

    @Resource
    private TavernDingSkTableDataMapper tavernDingSkTableDataMapper;

    @Resource
    private TavernAuditResultService tavernAuditResultService;


    @RequestMapping("/testTask")
    public ResponResult testTask() {
//        spiderHttpService.doDingProIdsTask("PROC-DBE92E71-EFF7-4D44-B389-C289907EA27D");
        return ResponResult.success();
    }


    /**
     * 宝寓房间财务信息
     * @param form
     * @return
     */
    @RequestMapping("/byRoomData")
    public ResponResult test(ByDataRoomForm form){
        // 获取当前月的第一天
        String monthFirstDay = DateUtils.getMonthFirstDay(0);
        String currDate = DateUtils.getCurrDate("yyyy-MM-dd");
//        form.setDate(monthFirstDay+","+currDate);
        form.setDate("2023-05-01,2023-05-31");
        form.setGroupBy("room");
        form.setMonth("202305");
        spiderHttpService.spiderByDataRoom(form);
        return ResponResult.success("HELLO_WORLD!");
    }

    /**
     * 宝寓保洁信息
     * @param form
     * @return
     */
    @RequestMapping("/byBjDataSync")
    public ResponResult byBjDataSync(ByDataRoomForm form) throws InterruptedException {
        // 获取当前月的第一天
//        String monthFirstDay = DateUtils.getMonthFirstDay(0);
//        String currDate = DateUtils.getCurrDate("yyyy-MM-dd");
        List<String> list = DateUtils.getDayListOfMonth("202305");
        for (String day : list) {
            form.setDate(day);
            form.setMonth("202305");
            spiderHttpService.byBjDataSync(form);
            Thread.sleep(DateUtils.getRandom(2,5)*1000);
        }
        return ResponResult.success("HELLO_WORLD!");
    }


    /**
     * 获取钉钉指定审批流程下所有的审批实例
     */
    @RequestMapping("/getProIds")
    public void getProIds() {
        Date start = DateUtils.String2Date("2023-06-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date end = DateUtils.String2Date("2023-06-30 23:59:59", "yyyy-MM-dd HH:mm:ss");
        log.info("getProIds.start:{},end:{}",start,end);
        // 收款审批
//        dingTalkDataApiService.getProInstanceIds(start,end,"PROC-B7FC540E-AEE5-4BAB-951E-F8CF55CDDE5C",1);

        // 财务报销审批
        dingTalkDataApiService.getProInstanceIds(start,end,"PROC-DBE92E71-EFF7-4D44-B389-C289907EA27D",2,"202306");
    }

    /**
     * 获取每个审批实例下的审批表单和表格数据
     */
    @RequestMapping("/instanceDetails")
    public void instanctDetails() {
//        List<TavernDingFormApproval> tavernDingFormApprovals = tavernDingSkApprovalMapper.selectList(
//                new QueryWrapper<TavernDingFormApproval>().lambda()
//                        .eq(TavernDingFormApproval::getDataTime, "20230517"));
//        List<String> collect = tavernDingFormApprovals.stream().map(TavernDingFormApproval::getProId).collect(Collectors.toList());


        QueryWrapper<TavernDingProData> qw = new QueryWrapper<>();
        qw.lambda().eq(TavernDingProData::getApprovalType,2);
        qw.lambda().eq(TavernDingProData::getDataTime,"20230701");
        qw.lambda().eq(TavernDingProData::getMonth,"202306");
        List<TavernDingProData> tavernDingProData = tavernDingProDataMapper.selectList(qw);
        log.info("tavernDingProData.size:{}",tavernDingProData.size());
        if (tavernDingProData.size() == 0) {
            return;
        }
//        dingTalkDataApiService.getProcessByInstanceId("xVAOy0sRTWaWY7UvqFybnA02311685609862",2,"");
//        tavernDingSkApprovalMapper.delete(
//                new QueryWrapper<TavernDingFormApproval>().lambda()
//                        .eq(TavernDingFormApproval::getDataTime,DateUtils.getTime(new Date(),"yyyyMMdd")));
//        tavernDingSkTableDataMapper.delete(
//                new QueryWrapper<TavernDingSkTableData>().lambda()
//                        .eq(TavernDingSkTableData::getDataTime,DateUtils.getTime(new Date(),"yyyyMMdd")));

        for (TavernDingProData proDatum : tavernDingProData) {
            String proId = proDatum.getProId();
            dingTalkDataApiService.getProcessByInstanceId(proId,2,"202306");
        }
    }

    /**
     * 根据请求日志重新解析钉钉报文 更新数据
     * @return
     */
    @RequestMapping("/updateDingForm")
    public ResponResult updateDingForm() {
        dingTalkDataApiService.updateDingForm();
        return ResponResult.success();
    }

    /**
     * 更新可编辑列表数据
     * @return
     */
    @RequestMapping("/insertResult")
    public ResponResult insertResult() {
//        tavernAuditResultService.insertResult(DateUtils.getCurrDate("yyyyMM"),"SYSTEM");
        tavernAuditResultService.insertResult("202306","SYSTEM");
        return ResponResult.success();
    }
}
