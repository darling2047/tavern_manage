package com.manage.tavern.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manage.tavern.constant.CommonConstant;
import com.manage.tavern.constant.UrlEnum;
import com.manage.tavern.mapper.ByBjDataMapper;
import com.manage.tavern.mapper.TavernBydataRoomMapper;
import com.manage.tavern.mapper.TavernDingProDataMapper;
import com.manage.tavern.mapper.TavernHttpLogMapper;
import com.manage.tavern.model.form.ByDataRoomForm;
import com.manage.tavern.model.vo.TavernByBjDataVo;
import com.manage.tavern.po.TavernByBjData;
import com.manage.tavern.po.TavernBydataRoom;
import com.manage.tavern.po.TavernDingProData;
import com.manage.tavern.po.sys.TavernHttpLog;
import com.manage.tavern.service.ByBjDataService;
import com.manage.tavern.service.DingTalkDataApiService;
import com.manage.tavern.service.SpiderHttpService;
import com.manage.tavern.utils.DateUtils;
import com.manage.tavern.utils.HttpUtils;
import com.manage.tavern.utils.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/4/9 8:55
 * @version:
 * @modified By:
 */
@Service
@Slf4j
public class SpiderHttpServiceImpl implements SpiderHttpService {

    @Value("${by.base.url}")
    private String baseUrl;

    @Value("${by.cookie}")
    private String cookie;

    @Resource
    private TavernBydataRoomMapper tavernBydataRoomMapper;

    @Resource
    private ByBjDataMapper byBjDataMapper;

    @Resource
    private ByBjDataService byBjDataService;

    @Resource
    private DingTalkDataApiService dingTalkDataApiService;

    @Resource
    private TavernHttpLogMapper tavernHttpLogMapper;

    @Resource
    private TavernDingProDataMapper tavernDingProDataMapper;

    @Override
    public void spiderByDataRoom(ByDataRoomForm form) {
        RestTemplate restTemplate = RestTemplateUtil.buildRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Cookie",cookie);
        String url = String.format(baseUrl, UrlEnum.BY_CW_ROOM_URL.getUrl());
        url += "?date="+form.getDate()+"&groupBy="+form.getGroupBy();
        TavernHttpLog httpLog = new TavernHttpLog();
        httpLog.setBusId(CommonConstant.HTTP_LOG_BUSI_IDS.BY_CW_AUDIT);
        httpLog.setUrl(url);
        String res = null;
        JSONArray arr = null;
        try {
            res = HttpUtils.doPost(restTemplate, headers, url, "");
            httpLog.setRes(res);
            JSONObject jsonObject = JSONObject.parseObject(res);
            arr = jsonObject.getJSONObject("data").getJSONObject("data").getJSONArray("rows");
        } catch (Exception e) {
            httpLog.setRes(e.getMessage());
            e.printStackTrace();
        }finally {
            tavernHttpLogMapper.insert(httpLog);
        }
        List<TavernBydataRoom> tavernBydataRooms = JSONObject.parseArray(arr.toJSONString(), TavernBydataRoom.class);
        String opTime = DateUtils.date2String(new Date(), "yyyyMMdd");
        delOptime(opTime);
        for (TavernBydataRoom tavernBydataRoom : tavernBydataRooms) {
            tavernBydataRoom.setOpTime(opTime);
            tavernBydataRoom.setMonth(form.getMonth());
            tavernBydataRoom.setCreateTime(new Date());
            tavernBydataRoomMapper.insert(tavernBydataRoom);
        }
        log.info("res:{}",res);
    }

    @Override
    public void byBjDataSync(ByDataRoomForm form) {
        RestTemplate restTemplate = RestTemplateUtil.buildRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Cookie",cookie);
        headers.set("User-Agent","PostmanRuntime/7.32.2");
        String url = "https://openapi.bypms.cn/openapi/room/task/v1/list?_dhsid_dh_sr_v3=7rkzkms73ufrohfq&date="+form.getDate()+"&state=E&workerId=0";
        TavernHttpLog httpLog = new TavernHttpLog();
        httpLog.setBusId(CommonConstant.HTTP_LOG_BUSI_IDS.BY_BJ);
        httpLog.setUrl(url);
        httpLog.setParams(form.getDate());
        String res = null;
        try {
            res = HttpUtils.doGet(restTemplate, url,headers);
            httpLog.setRes(res);
            log.info("byBjDataSync.res:{}",res);
        } catch (Exception e) {
            e.printStackTrace();
            httpLog.setRes(e.getMessage());
        } finally {
            tavernHttpLogMapper.insert(httpLog);
        }
        parseBjJson(res,form);
//        parseHtml(res,form);
    }

    @Override
    public void doDingProIdsTask(String formId) {
        // 获取上个月的第一天
        String monthFirstDay = DateUtils.getMonthFirstDay(-1,"yyyy-MM-dd");
        Date start = DateUtils.String2Date(monthFirstDay, "yyyy-MM-dd");
        // 获取当前月的第一天
        String currentFirstDay = DateUtils.getMonthFirstDay(0,"yyyy-MM-dd");
        Date end = DateUtils.String2Date(currentFirstDay, "yyyy-MM-dd");
        log.info("getProIds.start:{},end:{}",start,end);
        String lastMonth = DateUtils.getLastMonth("yyyyMM");
        int type = 0;
        if (Objects.equals(formId,"PROC-DBE92E71-EFF7-4D44-B389-C289907EA27D")) {
            type = 2;
        }
        // 财务报销审批
        dingTalkDataApiService.getProInstanceIds(start,end,"",type,lastMonth);
    }

    @Override
    public void dingProDetailsTask() {
        QueryWrapper<TavernDingProData> qw = new QueryWrapper<>();
        String lastMonth = DateUtils.getLastMonth("yyyyMM");
        qw.lambda().eq(TavernDingProData::getApprovalType,2);
        qw.lambda().eq(TavernDingProData::getMonth,lastMonth);
        List<TavernDingProData> tavernDingProData = tavernDingProDataMapper.selectList(qw);
        log.info("tavernDingProData.size:{}",tavernDingProData.size());
        if (tavernDingProData.size() == 0) {
            return;
        }
        for (TavernDingProData proDatum : tavernDingProData) {
            String proId = proDatum.getProId();
            dingTalkDataApiService.getProcessByInstanceId(proId,2,lastMonth);
        }
    }

    private synchronized void parseBjJson(String res, ByDataRoomForm form) {
        JSONObject data = JSONObject.parseObject(res).getJSONObject("data");
        List<TavernByBjDataVo> records = JSONObject.parseArray(data.getJSONArray("records").toJSONString(), TavernByBjDataVo.class);
        String opTime = DateUtils.date2String(new Date(), "yyyyMMdd");
        byBjDataMapper.delete(
                new QueryWrapper<TavernByBjData>().lambda().eq(TavernByBjData::getDataDate,form.getDate()));
        for (TavernByBjDataVo record : records) {
//            byBjDataMapper.delete(
//                    new QueryWrapper<TavernByBjData>().lambda()
//                            .eq(TavernByBjData::getDataDate,record.getTaskDate())
//                            .eq(TavernByBjData::getRoomName,record.getRoomNo()
//                    ));
            TavernByBjData bjData = new TavernByBjData();
            bjData.setDataDate(record.getTaskDate());
            bjData.setRoomName(record.getRoomNo());
            bjData.setBjName(record.getWorkerName());
            bjData.setType(record.getCleanLevelName());
            bjData.setPrice(record.getPrice());
            bjData.setWorkerMobile(record.getWorkerMobile());
            bjData.setPlanTime(record.getPlanTime());
            bjData.setStateName(record.getStateName());
            bjData.setEmplId(record.getEmplId());
            bjData.setEmplName(record.getEmplName());
            bjData.setTag(record.getTag());
            bjData.setOpTime(opTime);
            bjData.setMonth(form.getMonth());
            byBjDataService.save(bjData);
        }
    }

    private void parseHtml(String res,ByDataRoomForm form) {
        Document document = Jsoup.parse(res);
        Element lstTask = document.getElementById("lstTask");
        Element tbody = lstTask.select("table").select("tbody").get(0);
        Elements trs = tbody.getElementsByTag("tr");
        String opTime = DateUtils.date2String(new Date(), "yyyyMMdd");
        for (Element tr : trs) {
            TavernByBjData bjData = new TavernByBjData();
            Elements tds = tr.getElementsByTag("td");
            bjData.setDataDate(tds.get(0).text());
            bjData.setRoomName(tds.get(1).text());
            bjData.setBjName(tds.get(2).text());
            bjData.setType(tds.get(3).text());
            bjData.setPrice(tds.get(4).text());
            bjData.setRemark(tds.get(5).text());
            bjData.setOpTime(opTime);
            bjData.setMonth(form.getMonth());
            byBjDataService.save(bjData);
        }
    }

    private void delOptime(String opTime) {
        tavernBydataRoomMapper.delete(
                new QueryWrapper<TavernBydataRoom>().lambda().eq(TavernBydataRoom::getOpTime,opTime));
    }

}
