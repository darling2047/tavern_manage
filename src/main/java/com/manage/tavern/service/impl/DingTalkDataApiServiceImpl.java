package com.manage.tavern.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.manage.tavern.constant.CommonConstant;
import com.manage.tavern.constant.DingApiUrlEnum;
import com.manage.tavern.constant.FormItemIdsEnum;
import com.manage.tavern.mapper.*;
import com.manage.tavern.model.vo.SkApprovalFormItemVo;
import com.manage.tavern.model.vo.TavernDingFormApprovalVo;
import com.manage.tavern.model.vo.TavernDingSkTableDataVo;
import com.manage.tavern.po.TavernDingProData;
import com.manage.tavern.po.TavernDingFormApproval;
import com.manage.tavern.po.TavernDingSkTableData;
import com.manage.tavern.po.sys.TavernHttpLog;
import com.manage.tavern.service.DingTalkDataApiService;
import com.manage.tavern.utils.BeanCopierUtils;
import com.manage.tavern.utils.DateUtils;
import com.manage.tavern.utils.HttpUtils;
import com.manage.tavern.utils.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/4 16:06
 * @version:
 * @modified By:
 */
@Slf4j
@Service
public class DingTalkDataApiServiceImpl implements DingTalkDataApiService {

    @Value("${appkey}")
    private String appKey;

    @Value("${appsecret}")
    private String appSecret;

    @Value("${ding.base.url}")
    private String baseUrl;

    private static RestTemplate restTemplate;

    static {
        restTemplate = RestTemplateUtil.buildRestTemplate();
    }

    @Resource
    private TavernDingProDataMapper tavernDingProDataMapper;

    @Resource
    private TavernDingSkApprovalMapper tavernDingSkApprovalMapper;

    @Resource
    private TavernDingSkTableDataMapper tavernDingSkTableDataMapper;

    @Resource
    private TavernHttpLogMapper tavernHttpLogMapper;

    @Resource
    private RoomAuditMapper roomAuditMapper;

    @Override
    public void getProInstanceIds(Date startDate, Date endDate, String formId,Integer type,String month) {
        Long start = startDate.getTime();
        Long end = endDate.getTime();
        HttpHeaders headers = getHeader();
        JSONArray resArray = doItemPost(formId, start, end, headers, 1);
        String dataTime = DateUtils.getTime(new Date(), "yyyyMMdd");
        // 删除当天数据 如果有
//        tavernDingProDataMapper.delete(
//                new QueryWrapper<TavernDingProData>().lambda().eq(TavernDingProData::getDataTime,dataTime));
        for (Object proId : resArray) {
            TavernDingProData data = new TavernDingProData();
            data.setFormId(formId);
            data.setProId(proId.toString());
            data.setId(getId());
            data.setApprovalType(type);
            data.setDataTime(dataTime);
            data.setMonth(month);
            tavernDingProDataMapper.insert(data);
        }
    }

    @Override
    public synchronized List<TavernDingSkTableData> getProcessByInstanceId(String processInstanceId,Integer approvalType,String month) {
        Integer count = tavernHttpLogMapper.selectCount(
                new QueryWrapper<TavernHttpLog>()
                        .lambda().eq(TavernHttpLog::getBusId, processInstanceId));
        if (count > 0) {
            return null;
        }
        String url = baseUrl + DingApiUrlEnum.DING_INSTANCE_DETAILS_URL.getUrl() + "?processInstanceId="+processInstanceId;
        HttpHeaders headers = getHeader();
        TavernHttpLog httpLog = new TavernHttpLog();
        httpLog.setBusId(processInstanceId);
        httpLog.setUrl(url);
        String resStr = "";
        try {
            resStr = HttpUtils.doGet(restTemplate, url, headers);
            log.info("processInstanceId:{},getProcessByInstanceId.resStr:{}",processInstanceId,resStr);
            httpLog.setRes(resStr);
        }catch (Exception e){
            log.error("getProcessByInstanceId.error:{}",e.getMessage());
            httpLog.setRes(e.getMessage());
        }
        tavernHttpLogMapper.insert(httpLog);
        JSONObject jsonObject = JSONObject.parseObject(resStr);
        TavernDingFormApprovalVo approval = JSONObject.parseObject(jsonObject.getString("result"), TavernDingFormApprovalVo.class);
//        onlyUpdateTime(approval,processInstanceId);
//        return null;
        approval.setProId(processInstanceId);
        approval.setId(getId());
        approval.setApprovalType(approvalType);
        JSONArray arr = jsonObject.getJSONObject("result").getJSONArray("formComponentValues");
        for (int i = 0; i < arr.size(); i++) {
            SkApprovalFormItemVo formItemVo = JSONObject.parseObject(arr.getString(i), SkApprovalFormItemVo.class);
            String id = formItemVo.getId();
            String value = formItemVo.getValue();
            String name = formItemVo.getName();
            if (Objects.equals(id, FormItemIdsEnum.DING_FORM_CWSP_TABLE_AREA.getId()) || Objects.equals(name,"区域")) {
                approval.setArea(value);
            }else if (Objects.equals(id, FormItemIdsEnum.DING_FORM_CWSP_TABLE_DATA.getId()) || Objects.equals(name,"表格")) {
                // 考虑数据库空间 此处不存表格的json数据了
//                approval.setTableData(value);
                approval.setTableDataNonCloum(value);
            }else if (Objects.equals(id, FormItemIdsEnum.DING_FORM_CWSP_TABLE_FEE_SUM.getId()) || Objects.equals(name,"费用总计")) {
                approval.setFeeSum(value);
            }else if (Objects.equals(id, FormItemIdsEnum.DING_FORM_CWSP_TABLE_SK_TYPE.getId()) || Objects.equals(name,"收款方式")) {
                approval.setPayType(value);
            }else {
                continue;
            }
        }
        TavernDingFormApproval convert = BeanCopierUtils.convert(approval, TavernDingFormApproval.class);
        Date finishTime = DateUtils.dateConvert(approval.getFinishTime());
        Date createTime = DateUtils.dateConvert(approval.getCreateTime());
        convert.setFinishTime(finishTime);
        convert.setCreateTime(createTime);
        if (Objects.nonNull(finishTime)) {
            month = DateUtils.date2String(finishTime,"yyyyMM");
            convert.setMonth(month);
        }
        convert.setDataTime(DateUtils.getTime(new Date(),"yyyyMMdd"));
        tavernDingSkApprovalMapper.insert(convert);
        // 获取表格数据
        String tableData = approval.getTableDataNonCloum();
        List<TavernDingSkTableDataVo> tableDataVos = getTableDatas(tableData,convert.getId(),processInstanceId);
        List<TavernDingSkTableData> list = new ArrayList<>();
        for (TavernDingSkTableDataVo dataVo : tableDataVos) {
            TavernDingSkTableData data = BeanCopierUtils.convert(dataVo, TavernDingSkTableData.class);
            data.setId(getId());
            Date checkInTime = DateUtils.String2Date(dataVo.getCheckInTime(),"yyyy-MM-dd");
            Date outTime = DateUtils.String2Date(dataVo.getOutTime(),"yyyy-MM-dd");
            data.setCheckInTime(checkInTime);
            data.setOutTime(outTime);
            data.setId(getId());
            Date startDate = DateUtils.String2Date(dataVo.getStartDate(), "yyyy-MM-dd");
            Date endDate = DateUtils.String2Date(dataVo.getEndDate(), "yyyy-MM-dd");
            data.setStartDate(startDate);
            data.setEndDate(endDate);
            data.setMonth(month);
            data.setDataTime(DateUtils.getTime(new Date(),"yyyyMMdd"));
            tavernDingSkTableDataMapper.insert(data);
            list.add(data);
        }
        return list;
    }


    @Override
    public void updateDingForm() {
        List<TavernHttpLog> logs = tavernHttpLogMapper.selectList(
                new QueryWrapper<TavernHttpLog>().lambda().notIn(TavernHttpLog::getBusId, Arrays.asList("BY_BJ", "BY_CW_AUDIT")));
        for (TavernHttpLog httpLog : logs) {
            String res = httpLog.getRes();
            JSONObject jsonObject = JSONObject.parseObject(res);
            TavernDingFormApprovalVo approval = JSONObject.parseObject(jsonObject.getString("result"), TavernDingFormApprovalVo.class);
            System.out.println("approval = " + approval);
            String result = approval.getResult();
            String busId = httpLog.getBusId();
            if (StringUtils.isNotBlank(result) && StringUtils.isNotBlank(busId)) {
                roomAuditMapper.updateDingFormResult(result,busId);
            }
        }
    }

    private void onlyUpdateTime(TavernDingFormApprovalVo approval, String processInstanceId) {
        TavernDingFormApproval convert = new TavernDingFormApproval();
        convert.setProId(processInstanceId);
        Date finishTime = DateUtils.dateConvert(approval.getFinishTime());
        Date createTime = DateUtils.dateConvert(approval.getCreateTime());
        convert.setFinishTime(finishTime);
        convert.setCreateTime(createTime);
        convert.setDataTime(DateUtils.getTime(new Date(),"yyyyMMdd"));
        tavernDingSkApprovalMapper.update(convert
                ,new QueryWrapper<TavernDingFormApproval>().lambda()
                        .eq(TavernDingFormApproval::getProId,processInstanceId));
    }

    /**
     * 解析表格数据
     * @param tableData
     * @return
     */
    private List<TavernDingSkTableDataVo> getTableDatas(String tableData,String approvalId,String processInstanceId) {
        List<TavernDingSkTableDataVo> res = new ArrayList<>();
        JSONArray jsonArray = JSONObject.parseArray(tableData);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONArray rowValues = jsonArray.getJSONObject(i).getJSONArray("rowValue");
            TavernDingSkTableDataVo vo = new TavernDingSkTableDataVo();
            for (int j = 0; j < rowValues.size(); j++) {
                vo.setApprovalId(approvalId);
                vo.setProId(processInstanceId);
                JSONObject jsonObject = rowValues.getJSONObject(j);
                String key = jsonObject.getString("key");
                String value = jsonObject.getString("value");
                String label = jsonObject.getString("label");
                if (CommonConstant.DING_CWSKSP_LIST.contains(label) || CommonConstant.DING_CWSKBX_LIST.contains(label)) {
                    vo.setItemRoom(value);
                    continue;
                }
                if (CommonConstant.DING_HOUSE_KEEPER_LIST.contains(label)) {
                    vo.setHouseKeeper(value);
                    continue;
                }
                if (Objects.equals(label,"日常客耗名称")) {
                    vo.setKhName(value);
                }
                if (Objects.equals(label,"订单平台名称")) {
                    vo.setOrderPlatName(value);
                }
                if (Objects.equals(label,"实收房费")) {
                    vo.setActualFee(value);
                }
                if (Objects.equals(label,"手续费")) {
                    vo.setCommission(value);
                }
                if (Objects.equals(key,FormItemIdsEnum.DING_FORM_CWSP_TABLE_INNER_AREA.getId()) || Objects.equals(label,"区域")) {
                    vo.setItemArea(value);
                }else if (Objects.equals(key,FormItemIdsEnum.DING_FORM_CWSP_TABLE_INNER_FEE.getId()) || Objects.equals(label,"报销金额")) {
                    vo.setAmount(value);
                }else if (Objects.equals(key,FormItemIdsEnum.DING_FORM_CWSP_TABLE_INNER_FEE.getId()) || Objects.equals(label,"金额小计")) {
                    vo.setJexj(value);
                }else if (Objects.equals(key,FormItemIdsEnum.DING_FORM_CWSP_TABLE_INNER_REMARK.getId()) || Objects.equals(label,"费用明细说明")) {
                    vo.setRemark(value);
                }else if (Objects.equals(key,FormItemIdsEnum.DING_FORM_CWSP_TABLE_INNER_RESASON.getId())) {
                    vo.setItemReason(value);
                }else if (Objects.equals(key,FormItemIdsEnum.DING_FORM_CWSP_TABLE_INNER_SKPZ.getId()) || Objects.equals(label,"报销凭证")) {
//                    vo.setSkSupport(value);
                }else if (Objects.equals(key,FormItemIdsEnum.DING_FORM_CWBX_TABLE_INNER_BXTYPE.getId())) {
                    vo.setBxType(value);
                }else if (Objects.equals(label,"岗位")) {
                    vo.setGw(value);
                }else if (Objects.equals(key,FormItemIdsEnum.DING_FORM_CWSP_TABLE_INNER_CHECK_TIME.getId())) {
                    String[] split = value.split(",");
                    vo.setCheckInTime(split[0]);
                    vo.setOutTime(split[1]);
                }else if (Objects.equals(key,FormItemIdsEnum.DING_FORM_CWBX_TABLE_START_END_TIME.getId())) {
                    JSONArray array = JSONArray.parseArray(value);
                    vo.setStartDate(array.getString(0));
                    vo.setEndDate(array.getString(1));
                }else {
                    continue;
                }
            }
            res.add(vo);
        }
        return res;
    }

    private JSONArray doItemPost(String formId, Long start, Long end, HttpHeaders headers, int nextToken) {
        JSONObject params = getParams(start,end,formId,Long.valueOf(nextToken));
        String resStr = HttpUtils.doPost(restTemplate, headers, baseUrl+ DingApiUrlEnum.DING_INSTANCEIDS_URL.getUrl(), params);
        JSONObject jsonObject = JSONObject.parseObject(resStr);
        JSONObject result = jsonObject.getJSONObject("result");
        JSONArray itemArr = result.getJSONArray("list");
        Integer i = result.getInteger("nextToken");
        if (Objects.nonNull(i)) {
            JSONArray jsonArray = doItemPost(formId, start, end, headers, i);
            itemArr.addAll(jsonArray);
        }
        return itemArr;
    }

    /**
     * 拼接入参
     * @param start
     * @param end
     * @param formId
     * @return
     */
    private JSONObject getParams(Long start, Long end, String formId,Long nextToken) {
        JSONObject json = new JSONObject();
        json.put("startTime",start);
        json.put("endTime",end);
        json.put("processCode",formId);
        json.put("nextToken",nextToken);
        json.put("maxResults",20);
        return json;
    }

    private HttpHeaders getHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("x-acs-dingtalk-access-token",getAccessToken());
        return headers;
    }


    private String getAccessToken() {
//        String url = "https://oapi.dingtalk.com/gettoken?appkey=%s&appsecret=%s";
//        String format = String.format(url, appKey, appSecret);
//        String res = HttpUtils.doGet(restTemplate, format);
//        log.info("getAccessToken.res:{}",res);
//        if (StringUtils.isBlank(res)) {
//            throw new RuntimeException("获取AccessToken失败");
//        }
//        JSONObject jsonObject = JSONObject.parseObject(res);
//        String accessToken = jsonObject.getString("access_token");
//        return accessToken;
        return "ca671af984d636d489e8bffd2441a8cb";
    }

    private String getId(){
        return UUID.randomUUID().toString().substring(0,32);
    }

    public static void main(String[] args) {
        Date date = DateUtils.String2Date("2023-04-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println("date.getTime() = " + date.getTime());
    }
}
