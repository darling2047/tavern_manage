package com.manage.tavern.controller;

import com.manage.tavern.annotate.SysLog;
import com.manage.tavern.model.wechat.WeChatForm;
import com.manage.tavern.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/6/5 22:17
 * @version:
 * @modified By:
 */
@RequestMapping("/suite")
@RestController
@Slf4j
public class WeChatCallBackController {


    @SysLog("receive回调")
    @RequestMapping("/receive")
    public String receive(HttpServletRequest request, WeChatForm form) {
        try {
            UserUtils.setRequest(request);
            log.info("receive.form:{}",form.toString());
            Map<String, String[]> parameterMap = request.getParameterMap();
            Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
            for (Map.Entry<String, String[]> entry : entries) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                log.info("☆☆☆☆☆☆☆☆☆receive.key:{},values:{}",key,value);
            }
        } catch (Exception e) {
            log.error("errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrr:{}",e.getMessage());
            e.printStackTrace();
        }
        return "success";
    }
}
