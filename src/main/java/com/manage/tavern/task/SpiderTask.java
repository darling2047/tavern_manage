package com.manage.tavern.task;

import com.manage.tavern.model.form.ByDataRoomForm;
import com.manage.tavern.service.SpiderHttpService;
import com.manage.tavern.service.TavernAuditResultService;
import com.manage.tavern.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/4/17 8:44
 * @version:
 * @modified By:
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class SpiderTask {


    @Resource
    private SpiderHttpService spiderHttpService;

    @Resource
    private TavernAuditResultService tavernAuditResultService;


    /**
     * 每日22点爬取宝寓财务数据
     */
    @Scheduled(cron = "0 0 22 * * ?")
    public void hrUserInfoSync() {
        long l = System.currentTimeMillis();
        log.info("time:{},{}",l,"com.manage.tavern.task.SpiderTask.hrUserInfoSync.start...");
        ByDataRoomForm form = new ByDataRoomForm();
        // 获取当前月的第一天
        String monthFirstDay = DateUtils.getMonthFirstDay(0);
        String currDate = DateUtils.getCurrDate("yyyy-MM-dd");
        form.setDate(monthFirstDay+","+currDate);
        form.setGroupBy("room");
        form.setMonth(DateUtils.getTime(new Date(), "yyyyMM"));
        spiderHttpService.spiderByDataRoom(form);
        log.info("time:{},{}",System.currentTimeMillis()-l,"com.manage.tavern.task.SpiderTask.hrUserInfoSync.end...");
    }


    /**
     * 每日21点爬取宝寓保洁数据
     */
    @Scheduled(cron = "0 0 21 * * ?")
    public void byBjDataSync() {
        long l = System.currentTimeMillis();
        log.info("com.manage.tavern.task.SpiderTask.bjDataSync.start...time:{}",l);
        ByDataRoomForm form = new ByDataRoomForm();
        // 获取当前月的第一天
        String monthFirstDay = DateUtils.getMonthFirstDay(0);
        String currDate = DateUtils.getCurrDate("yyyy-MM-dd");
        form.setDate(currDate);
        form.setGroupBy("room");
        form.setMonth(DateUtils.getTime(new Date(), "yyyyMM"));
        spiderHttpService.byBjDataSync(form);
        log.info("com.manage.tavern.task.SpiderTask.bjDataSync.end...time:{}",System.currentTimeMillis()-l);
    }


    /**
     * 每月1号20点重新获取上月所有保洁数据
     */
    @Scheduled(cron = "0 0 20 1 * ? ")
    public void byBjDataMonthSync() throws InterruptedException {
        long l = System.currentTimeMillis();
        log.info("com.manage.tavern.task.SpiderTask.byBjDataMonthSync.start...time:{}",l);
        // 获取上月每一天的列表
        List<String> list = DateUtils.getDayListOfMonth(DateUtils.getLastMonth("yyyyMM"));
        for (String day : list) {
            ByDataRoomForm form = new ByDataRoomForm();
            form.setDate(day);
            Date date = DateUtils.String2Date(day, "yyyy-MM-dd");
            form.setMonth(DateUtils.getTime(date, "yyyyMM"));
            spiderHttpService.byBjDataSync(form);
            Thread.sleep(DateUtils.getRandom(2,5)*1000);
        }
        log.info("com.manage.tavern.task.SpiderTask.byBjDataMonthSync.end...time:{}",System.currentTimeMillis()-l);
    }


    /**
     * 每月第一天凌晨3点获取当月钉钉财务报销流程下的所有实例
     */
    @Scheduled(cron = "0 0 3 1 * ? ")
    public void dingProIdsTask() {
        long l = System.currentTimeMillis();
        log.info("com.manage.tavern.task.SpiderTask.dingProIdsTask.start...time:{}",l);
        spiderHttpService.doDingProIdsTask("PROC-DBE92E71-EFF7-4D44-B389-C289907EA27D");
        log.info("com.manage.tavern.task.SpiderTask.dingProIdsTask.end...time:{}",System.currentTimeMillis()-l);
    }


    /**
     * 每月第一天凌晨6点获取当月钉钉财务报销流程下的所有实例详情
     */
    @Scheduled(cron = "0 0 6 1 * ? ")
    public void dingProDetailsTask() {
        long l = System.currentTimeMillis();
        log.info("com.manage.tavern.task.SpiderTask.dingProDetailsTask.start...time:{}",l);
        spiderHttpService.dingProDetailsTask();
        log.info("com.manage.tavern.task.SpiderTask.dingProDetailsTask.end...time:{}",System.currentTimeMillis()-l);
    }

//    /**
//     * 每周三中午12点更新可编辑列表
//     */
//    @Scheduled(cron = "0 0 12 ? * WED")
//    public void editResultListTask() {
//        long l = System.currentTimeMillis();
//        log.info("com.manage.tavern.task.SpiderTask.editResultListTask.start...time:{}",l);
//        try {
//            tavernAuditResultService.insertResult(DateUtils.getCurrDate("yyyyMM"),"SYSTEM");
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("editResultListTask.error:{}",e.getMessage());
//        }
//        log.info("com.manage.tavern.task.SpiderTask.editResultListTask.end...time:{}",System.currentTimeMillis()-l);
//    }


    public static void main(String[] args) throws ParseException {
//        Date finishTime = DateUtils.dateConvert("2023-06-02T09:20Z");
//        System.out.println("finishTime = " + finishTime);
//        String date2String = DateUtils.date2String(finishTime, "yyyy-MM-dd HH:mm:ss");
//        System.out.println("date2String = " + date2String);
    }

}
