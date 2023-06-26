package com.manage.tavern.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author dll
 * @create 2021/9/14 19:10
 * @describe
 */
@Slf4j
public class HttpUtils {

    /**
     * 发送post请求
     * @param restTemplate
     * @param headers
     * @param url
     * @param param json格式入参
     * @return
     */
    public static String doPost(RestTemplate restTemplate,HttpHeaders headers,String url, String param) {
        log.info("接口入参："+param + "☆☆☆☆☆");
        HttpEntity<String> request = new HttpEntity<>(param,headers);
        ResponseEntity<String> smsMsgRes = restTemplate.postForEntity(url, request, String.class);
        log.info("接口回执："+smsMsgRes.getBody() + "☆☆☆☆☆");
        return smsMsgRes.getBody();
    }

    /**
     * 发送post请求
     * @param restTemplate
     * @param headers
     * @param url
     * @param params
     * @return
     */
    public static String doPost(RestTemplate restTemplate,HttpHeaders headers,String url, JSONObject params) {
        String param = JSONObject.toJSONString(params);
        log.info("接口入参："+param + "☆☆☆☆☆;url:{}",url);
        HttpEntity<String> request = new HttpEntity<>(param,headers);
        ResponseEntity<String> smsMsgRes = restTemplate.postForEntity(url, request, String.class);
        log.info("接口回执："+smsMsgRes.getBody() + "☆☆☆☆☆;url:{}",url);
        return smsMsgRes.getBody();
    }

    public static String doPost(RestTemplate restTemplate,String url, MultiValueMap multiValueMap) {
        String res = restTemplate.postForObject(url, multiValueMap, String.class);
        return res;
    }

    /**
     * 发送get请求  入参是map
     * @param restTemplate
     * @param url
     * @param param
     * @return
     */
    public static String doGet(RestTemplate restTemplate,String url, Map<String,Object> param) {
        log.info("接口入参："+param + "☆☆☆☆☆");
        ResponseEntity<String> smsMsgRes = restTemplate.getForEntity(url,String.class,param);
        log.info("接口回执："+smsMsgRes.getBody() + "☆☆☆☆☆");
        return smsMsgRes.getBody();
    }

    /**
     * 发送get请求  入参在url上拼接
     * @param restTemplate
     * @param url
     * @return
     */
    public static String doGet(RestTemplate restTemplate,String url) {
        RestTemplate restTemplate1= new RestTemplate(new HttpComponentsClientHttpRequestFactory());
        restTemplate1.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        ResponseEntity<String> smsMsgRes = restTemplate1.getForEntity(url,String.class);
        return smsMsgRes.getBody();
    }

    /**
     * get请求 带请求头
     * @param restTemplate
     * @param url
     * @param headers
     * @return
     */
    public static String doGet(RestTemplate restTemplate,String url,HttpHeaders headers) {
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class,
                1
        );
        return response.getBody();

    }



}
