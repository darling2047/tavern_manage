package com.manage.tavern.utils;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author dll
 * @create 2020/5/30 10:06
 * @describe
 */
public class WebContextUtils extends WebContext {

    private static final Logger logger = LoggerFactory.getLogger(WebContextUtils.class);

    public WebContextUtils() {
    }


    public static <T> void setSessionUser(T t) {
        try {
            getSession().setAttribute("user", t);
        } catch (Exception var2) {
            logger.error(var2.getMessage(), var2);
        }

    }

    public static void removeSessionUser() {
        try {
            getSession().removeAttribute("user");
        } catch (Exception var1) {
            logger.error(var1.getMessage(), var1);
        }

    }


    public static void removeBusiUser() {
        getSession().removeAttribute("user");
    }

    public static void responseOutWithJson(HttpServletResponse response, Object object) {
        ServletOutputStream out = null;

        try {
            String responseJSONObject = JSON.toJSONString(object);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-type", "application/json;charset=UTF-8");
            out = response.getOutputStream();
            out.write(responseJSONObject.getBytes("utf-8"));
            out.flush();
        } catch (IOException var12) {
            logger.error(var12.getMessage(), var12);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException var11) {
                    var11.printStackTrace();
                }
            }

        }

    }
}
