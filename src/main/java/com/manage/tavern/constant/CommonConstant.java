package com.manage.tavern.constant;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/4/9 9:08
 * @version:
 * @modified By:
 */
public class CommonConstant {


    public static List<String> DING_CWSKSP_LIST = Arrays.asList("公司","杭州一区","杭州二区","杭州三区","杭州四区","重庆"
            ,"成都","成都二区","西安","长沙","海口","公司新部门");

    public static List<String> DING_CWSKBX_LIST = Arrays.asList("办公室及宿舍房屋名称","杭州一区房屋名称","杭州二区房屋名称"
            ,"杭州三区房屋名称","杭州四区房屋名称","重庆房屋名称","成都房屋名称","成都二区房屋名称","西安房屋名称","长沙房屋名称"
            ,"海口房屋名称","公司新部门");

    public static List<String> DING_HOUSE_KEEPER_LIST = Arrays.asList("公司","杭州地区保洁管家","重庆地区保洁管家"
            ,"成都地区保洁管家","西安地区保洁管家","南京地区保洁管家","其他地区");

    public static List<String> NET_PRO_FITS_ZERO_LIST = Arrays.asList("张老师","陈龙","世贸广场402","星悦城23-1-101");


    public static List<String> SETTLEMENTAMOUNT_OTHER_LIST = Arrays.asList("张老师","陈龙","世贸广场402","重庆亦琳");

    public static List<String> BJ_TEST_DATE = Arrays.asList("2023-05-04","2023-05-05","2023-05-06","2023-05-07"
            ,"2023-05-08","2023-05-09","2023-05-10","2023-05-11","2023-05-12","2023-05-13"
            ,"2023-05-14","2023-05-15","2023-05-16");


    /**
     * 不需要登录
     */
    public static final Integer NONEED_LOGIN = 0;

    /**
     * 菜单为子节点(有url)
     */
    public static final Integer MENU_LEAF = 1;

    /**
     * 系统支持的角色类型
     */
    public interface ROLE_TYPE {

        /**
         * 主管理员
         */
        String MASTER_ADMINISTRATOR = "ROLE_0001";

        /**
         * 子管理员
         */
        String SUB_ADMINISTRATOR = "ROLE_0002";

        /**
         * 部门设备管理员
         */
        String DEPT_DEVICE_ADMINISTRATOR = "ROLE_0003";

        /**
         * 部门领导
         */
        String DEPT_LEADER = "ROLE_0004";

        /**
         * 普通保管员
         */
        String CUSTODIAN = "ROLE_0005";

    }

    /**
     * 布草单价
     */
    public interface BC_PRICE {

        /**
         * 床单
         */
        BigDecimal cd = new BigDecimal("1.9");

        /**
         * 被套
         */
        BigDecimal bt = new BigDecimal("2.1");

        /**
         * 枕套
         */
        BigDecimal zt = new BigDecimal("1.0");

        /**
         * 浴巾
         */
        BigDecimal yj = new BigDecimal("1.2");

        /**
         * 毛巾
         */
        BigDecimal mj = new BigDecimal("1.0");
    }

    /**
     * 布草总价
     */
    public static class BC_PRICE_SUM {

        static BigDecimal ONE = new BigDecimal("10.4");

        static BigDecimal TWO = new BigDecimal("16.4");

        static BigDecimal THREE = new BigDecimal("24.6");

        static BigDecimal FOUR = new BigDecimal("32.8");

        static BigDecimal FIVE = new BigDecimal("41");

        static BigDecimal SIX = new BigDecimal("49.2");

        static BigDecimal SEVEN = new BigDecimal("57.4");

        static BigDecimal EIGHT = new BigDecimal("65.6");

        static HashMap<Integer,BigDecimal> map = new HashMap();

        static {
            map.put(1,ONE);
            map.put(2,TWO);
            map.put(3,THREE);
            map.put(4,FOUR);
            map.put(5,FIVE);
            map.put(6,SIX);
            map.put(7,SEVEN);
            map.put(8,EIGHT);
        }

        public static BigDecimal getBcSum(Integer roomType) {
            return map.get(roomType);
        }
    }

    /**
     * 网络调用日志业务ID
     */
    public interface HTTP_LOG_BUSI_IDS {

        /**
         * 宝寓财务信息调用
         */
        String BY_CW_AUDIT = "BY_CW_AUDIT";

        /**
         * 宝寓保洁信息调用
         */
        String BY_BJ = "BY_BJ";

    }

    public static void main(String[] args) {
        BigDecimal a = BC_PRICE.cd.multiply(new BigDecimal(1));
        BigDecimal b = BC_PRICE.cd.multiply(new BigDecimal(2));
        BigDecimal c = BC_PRICE.cd.multiply(new BigDecimal(3));
        BigDecimal d = BC_PRICE.cd.multiply(new BigDecimal(4));
        BigDecimal add = a.add(b).add(c).add(d);
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        System.out.println("d = " + d);
        System.out.println("add = " + add);
    }
}
