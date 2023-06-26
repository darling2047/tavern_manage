package com.manage.tavern.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manage.tavern.constant.FormItemIdsEnum;
import com.manage.tavern.model.vo.SkApprovalFormItemVo;
import com.manage.tavern.model.vo.TavernDingFormApprovalVo;

import java.text.ParseException;
import java.util.Objects;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/5/5 8:32
 * @version:
 * @modified By:
 */
public class Test {

    public static String  res = "{\n" +
            "    \"result\": {\n" +
            "        \"finishTime\": \"2023-04-08T20:40Z\",\n" +
            "        \"attachedProcessInstanceIds\": [],\n" +
            "        \"businessId\": \"202304071651000424934\",\n" +
            "        \"title\": \"夏小翔提交的收款审批\",\n" +
            "        \"originatorDeptId\": \"340839129\",\n" +
            "        \"operationRecords\": [\n" +
            "            {\n" +
            "                \"date\": \"2023-04-07T16:51Z\",\n" +
            "                \"result\": \"NONE\",\n" +
            "                \"type\": \"START_PROCESS_INSTANCE\",\n" +
            "                \"userId\": \"01091101153622673140\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"2023-04-07T16:51Z\",\n" +
            "                \"result\": \"NONE\",\n" +
            "                \"ccUserIds\": [\n" +
            "                    \"014829342326487466\",\n" +
            "                    \"126158143036242324\"\n" +
            "                ],\n" +
            "                \"remark\": \"\",\n" +
            "                \"type\": \"PROCESS_CC\",\n" +
            "                \"userId\": \"01091101153622673140\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"2023-04-07T18:23Z\",\n" +
            "                \"result\": \"AGREE\",\n" +
            "                \"remark\": \"\",\n" +
            "                \"type\": \"EXECUTE_TASK_NORMAL\",\n" +
            "                \"userId\": \"051347086437340999\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"2023-04-07T21:38Z\",\n" +
            "                \"result\": \"AGREE\",\n" +
            "                \"remark\": \"\",\n" +
            "                \"type\": \"EXECUTE_TASK_NORMAL\",\n" +
            "                \"userId\": \"0924094468840252\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"2023-04-07T23:34Z\",\n" +
            "                \"result\": \"AGREE\",\n" +
            "                \"remark\": \"\",\n" +
            "                \"type\": \"EXECUTE_TASK_NORMAL\",\n" +
            "                \"userId\": \"34064161948026\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"2023-04-08T20:40Z\",\n" +
            "                \"result\": \"AGREE\",\n" +
            "                \"remark\": \"\",\n" +
            "                \"type\": \"EXECUTE_TASK_NORMAL\",\n" +
            "                \"userId\": \"324002494721468692\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"date\": \"2023-04-08T20:40Z\",\n" +
            "                \"result\": \"NONE\",\n" +
            "                \"ccUserIds\": [\n" +
            "                    \"041267533537623070\"\n" +
            "                ],\n" +
            "                \"remark\": \"\",\n" +
            "                \"type\": \"PROCESS_CC\",\n" +
            "                \"userId\": \"01091101153622673140\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"formComponentValues\": [\n" +
            "            {\n" +
            "                \"componentType\": \"DDSelectField\",\n" +
            "                \"name\": \"区域\",\n" +
            "                \"bizAlias\": \"\",\n" +
            "                \"id\": \"DDSelectField_1IPMPA32H9CW0\",\n" +
            "                \"value\": \"西安\",\n" +
            "                \"extValue\": \"{\\\"label\\\":\\\"西安\\\",\\\"key\\\":\\\"option_3KS3F8IWT640\\\"}\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"componentType\": \"TableField\",\n" +
            "                \"name\": \"表格\",\n" +
            "                \"id\": \"TableField-K9FCISUT\",\n" +
            "                \"value\": \"[{\\\"rowValue\\\":[{\\\"label\\\":\\\"区域\\\",\\\"extendValue\\\":{\\\"label\\\":\\\"西安\\\",\\\"key\\\":\\\"option_XCJEU19GKYO0\\\"},\\\"value\\\":\\\"西安\\\",\\\"key\\\":\\\"DDSelectField-K9FCISUP\\\"},{\\\"label\\\":\\\"西安\\\",\\\"extendValue\\\":{\\\"label\\\":\\\"壹又贰分之壹B-811\\\",\\\"key\\\":\\\"option_21X8SX61VJ1C0\\\"},\\\"value\\\":\\\"壹又贰分之壹B-811\\\",\\\"key\\\":\\\"DDSelectField_5QWMCOZUOA40\\\"},{\\\"label\\\":\\\"收款原因\\\",\\\"extendValue\\\":{\\\"label\\\":\\\"客人续住\\\",\\\"key\\\":\\\"option_T0DPHP4XIWG0\\\"},\\\"value\\\":\\\"客人续住\\\",\\\"key\\\":\\\"DDSelectField_1DZRASLX70JK0\\\"},{\\\"label\\\":[\\\"入住时间\\\",\\\"退房时间\\\"],\\\"value\\\":[\\\"2023-03-24\\\",\\\"2023-03-25\\\",null],\\\"key\\\":\\\"DDDateRangeField-K9FCISUQ\\\"},{\\\"label\\\":\\\"金额\\\",\\\"value\\\":\\\"400\\\",\\\"key\\\":\\\"MoneyField-K9FCISUS\\\"},{\\\"label\\\":\\\"备注\\\",\\\"value\\\":\\\"续住\\\",\\\"key\\\":\\\"TextField-K9L77ZOZ\\\"},{\\\"label\\\":\\\"收款凭证\\\",\\\"value\\\":[{\\\"spaceId\\\":\\\"3459494126\\\",\\\"fileName\\\":\\\"2wuy10sopab73qismzkb1u1rs_0_[B@10c06da.jpg\\\",\\\"fileSize\\\":101252,\\\"fileType\\\":\\\"jpg\\\",\\\"fileId\\\":\\\"101222054637\\\"}],\\\"key\\\":\\\"DDAttachment-K9FCNPBQ\\\"}],\\\"rowNumber\\\":\\\"TableField-K9FCISUT_1CQ6W1T3Y8DC0\\\"}]\",\n" +
            "                \"extValue\": \"{\\\"statValue\\\":[],\\\"componentName\\\":\\\"TableField\\\"}\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"componentType\": \"CalculateField\",\n" +
            "                \"name\": \"费用总计\",\n" +
            "                \"id\": \"CalculateField-K9FCNPBP\",\n" +
            "                \"value\": \"400\",\n" +
            "                \"extValue\": \"{\\\"upper\\\":\\\"肆佰元整\\\",\\\"componentName\\\":\\\"MoneyField\\\"}\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"componentType\": \"DDSelectField\",\n" +
            "                \"name\": \"收款方式\",\n" +
            "                \"id\": \"DDSelectField-IH2XSZK9\",\n" +
            "                \"value\": \"微信支付\",\n" +
            "                \"extValue\": \"{\\\"label\\\":\\\"微信支付\\\",\\\"key\\\":\\\"option_JZ3TW61Q\\\"}\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"result\": \"agree\",\n" +
            "        \"bizAction\": \"NONE\",\n" +
            "        \"ccUserIds\": [\n" +
            "            \"126158143036242324\",\n" +
            "            \"014829342326487466\",\n" +
            "            \"041267533537623070\"\n" +
            "        ],\n" +
            "        \"createTime\": \"2023-04-07T16:51Z\",\n" +
            "        \"originatorUserId\": \"01091101153622673140\",\n" +
            "        \"tasks\": [\n" +
            "            {\n" +
            "                \"result\": \"AGREE\",\n" +
            "                \"activityId\": \"897a_8399\",\n" +
            "                \"finishTime\": \"2023-04-08T20:40Z\",\n" +
            "                \"pcUrl\": \"aflow.dingtalk.com?procInsId=uTf3jGM-RhusS6e8U95iCQ02311680857502&taskId=79233352026&businessId=202304071651000424934\",\n" +
            "                \"createTime\": \"2023-04-07T23:34Z\",\n" +
            "                \"mobileUrl\": \"aflow.dingtalk.com?procInsId=uTf3jGM-RhusS6e8U95iCQ02311680857502&taskId=79233352026&businessId=202304071651000424934\",\n" +
            "                \"userId\": \"324002494721468692\",\n" +
            "                \"taskId\": 79233352026,\n" +
            "                \"status\": \"COMPLETED\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"result\": \"AGREE\",\n" +
            "                \"activityId\": \"789e_7f55\",\n" +
            "                \"finishTime\": \"2023-04-07T21:38Z\",\n" +
            "                \"pcUrl\": \"aflow.dingtalk.com?procInsId=uTf3jGM-RhusS6e8U95iCQ02311680857502&taskId=79303450002&businessId=202304071651000424934\",\n" +
            "                \"createTime\": \"2023-04-07T18:23Z\",\n" +
            "                \"mobileUrl\": \"aflow.dingtalk.com?procInsId=uTf3jGM-RhusS6e8U95iCQ02311680857502&taskId=79303450002&businessId=202304071651000424934\",\n" +
            "                \"userId\": \"0924094468840252\",\n" +
            "                \"taskId\": 79303450002,\n" +
            "                \"status\": \"COMPLETED\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"result\": \"AGREE\",\n" +
            "                \"activityId\": \"d5f4_6b02\",\n" +
            "                \"finishTime\": \"2023-04-07T18:23Z\",\n" +
            "                \"pcUrl\": \"aflow.dingtalk.com?procInsId=uTf3jGM-RhusS6e8U95iCQ02311680857502&taskId=79342476000&businessId=202304071651000424934\",\n" +
            "                \"createTime\": \"2023-04-07T16:51Z\",\n" +
            "                \"mobileUrl\": \"aflow.dingtalk.com?procInsId=uTf3jGM-RhusS6e8U95iCQ02311680857502&taskId=79342476000&businessId=202304071651000424934\",\n" +
            "                \"userId\": \"051347086437340999\",\n" +
            "                \"taskId\": 79342476000,\n" +
            "                \"status\": \"COMPLETED\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"result\": \"AGREE\",\n" +
            "                \"activityId\": \"4a08_a5fd\",\n" +
            "                \"finishTime\": \"2023-04-07T23:34Z\",\n" +
            "                \"pcUrl\": \"aflow.dingtalk.com?procInsId=uTf3jGM-RhusS6e8U95iCQ02311680857502&taskId=79347335554&businessId=202304071651000424934\",\n" +
            "                \"createTime\": \"2023-04-07T21:38Z\",\n" +
            "                \"mobileUrl\": \"aflow.dingtalk.com?procInsId=uTf3jGM-RhusS6e8U95iCQ02311680857502&taskId=79347335554&businessId=202304071651000424934\",\n" +
            "                \"userId\": \"34064161948026\",\n" +
            "                \"taskId\": 79347335554,\n" +
            "                \"status\": \"COMPLETED\"\n" +
            "            }\n" +
            "        ],\n" +
            "        \"originatorDeptName\": \"运营总监-运营人员\",\n" +
            "        \"status\": \"COMPLETED\"\n" +
            "    },\n" +
            "    \"success\": true\n" +
            "}";

    public static void main(String[] args) throws ParseException {
        JSONObject jsonObject = JSONObject.parseObject(res);
        TavernDingFormApprovalVo approval = JSONObject.parseObject(jsonObject.getString("result"), TavernDingFormApprovalVo.class);
        JSONArray arr = jsonObject.getJSONObject("result").getJSONArray("formComponentValues");
        for (int i = 0; i < arr.size(); i++) {
            SkApprovalFormItemVo formItemVo = JSONObject.parseObject(arr.getString(i), SkApprovalFormItemVo.class);
            String id = formItemVo.getId();
            String value = formItemVo.getValue();
            if (Objects.equals(id, FormItemIdsEnum.DING_FORM_CWSP_TABLE_AREA.getId())) {
                approval.setArea(value);
            }else if (Objects.equals(id, FormItemIdsEnum.DING_FORM_CWSP_TABLE_DATA.getId())) {
                approval.setTableData(value);
            }else if (Objects.equals(id, FormItemIdsEnum.DING_FORM_CWSP_TABLE_FEE_SUM.getId())) {
                approval.setFeeSum(value);
            }else if (Objects.equals(id, FormItemIdsEnum.DING_FORM_CWSP_TABLE_SK_TYPE.getId())) {
                approval.setPayType(value);
            }else {
                continue;
            }
        }
        System.out.println("approval = " + approval);

        //此方法是将2017-11-18T07:12:06.615Z格式的时间转化为秒为单位的Long类型。
//        String time = "2023-04-08T20:40Z";
//        time = time.replace("Z", " UTC");//UTC是本地时间
//        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd'T'HH:mm Z");
//        Date d = null;
//        try {
//            d = format.parse(time);
//        } catch (ParseException e) {
//            e.printStackTrace();
        }
        //此处是将date类型装换为字符串类型，比如：Sat Nov 18 15:12:06 CST 2017转换为2017-11-18 15:12:06
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date = sf.format(d);
//        System.out.println(d);//这里输出的是date类型的时间
//        System.out.println(d.getTime()/1000);//这里输出的是以秒为单位的long类型的时间。如果需要一毫秒为单位，可以不用除1000.System.out.println(sf.format(d));//这里输出的是字符串类型的时间

//    }

}
