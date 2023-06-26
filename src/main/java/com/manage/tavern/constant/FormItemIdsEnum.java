package com.manage.tavern.constant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @Author   dll
 * @create   2023/4/9 09:09
 */
@AllArgsConstructor
@NoArgsConstructor
public enum FormItemIdsEnum {


    DING_FORM_CWSP_TABLE_AREA("DDSelectField_1IPMPA32H9CW0", "区域"),
    DING_FORM_CWSP_TABLE_DATA("TableField-K9FCISUT", "表格"),
    DING_FORM_CWSP_TABLE_FEE_SUM("CalculateField-K9FCNPBP", "费用总计"),
    DING_FORM_CWSP_TABLE_SK_TYPE("DDSelectField-IH2XSZK9", "收款方式"),
    DING_FORM_CWSP_TABLE_INNER_AREA("DDSelectField-K9FCISUP", "表格内选择的区域"),
    DING_FORM_CWSP_TABLE_INNER_ROOM("DDSelectField_CWS3OUKP01K0", "表格内选择的房号"),
    DING_FORM_CWSP_TABLE_INNER_RESASON("DDSelectField_1DZRASLX70JK0", "表格内收款原因"),
    DING_FORM_CWSP_TABLE_INNER_FEE("MoneyField-K9FCISUS", "表格内金额"),
    DING_FORM_CWSP_TABLE_INNER_REMARK("TextField-K9L77ZOZ", "表格内备注"),
    DING_FORM_CWSP_TABLE_INNER_SKPZ("DDAttachment-K9FCNPBQ", "表格内收款凭证"),
    DING_FORM_CWSP_TABLE_INNER_CHECK_TIME("DDDateRangeField-K9FCISUQ", "表格内入住/退房时间"),
    DING_FORM_CWBX_TABLE_START_END_TIME("DDDateRangeField_PNQ9I3E70MO0", "表格内开始/结束时间"),
    DING_FORM_CWBX_TABLE_INNER_BXTYPE("DDSelectField-K9FA4FVW", "表格内报销品类");


    private String id;

    private String name;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

}
