package com.manage.tavern.controller.sys;

import com.manage.tavern.annotate.SysLog;
import com.manage.tavern.constant.CommonConstant;
import com.manage.tavern.constant.SessionConstant;
import com.manage.tavern.model.base.MenuInfoModel;
import com.manage.tavern.model.base.ResponResult;
import com.manage.tavern.model.wechat.WeChatForm;
import com.manage.tavern.po.sys.DmSysUser;
import com.manage.tavern.po.sys.DmSysUserRole;
import com.manage.tavern.po.sys.UserInfoModel;
import com.manage.tavern.service.ISystemService;
import com.manage.tavern.utils.BeanCopierUtils;
import com.manage.tavern.utils.EcbUtils;
import com.manage.tavern.utils.UserUtils;
import com.manage.tavern.wechat.AesException;
import com.manage.tavern.wechat.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author dll
 * @create 2020/5/31 11:12
 * @describe 各系统功能API入口
 */
@RequestMapping("/system")
@RestController
@Slf4j
public class SystemController {

    @Value("${common.need-login}")
    private Integer needLogin;

    @Resource
    private ISystemService systemService;

    @SysLog("登录")
    @RequestMapping("/doLogin")
    public ResponResult doLogin(String loginName, String password,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserUtils.setRequest(request);
            password = EcbUtils.decryptAES(password);
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            return ResponResult.failed("用户名密码不能为空!");
        }
        DmSysUser userInfo = systemService.getUserByLoginName(loginName);
        if (Objects.isNull(userInfo)) {
            return ResponResult.failed("用户名不存在,请确认后重试!");
        }
        if (!Objects.equals(userInfo.getPassword(), password)) {
            return ResponResult.failed("密码错误,请确认后重试!");
        }
        UserInfoModel convert = BeanCopierUtils.convert(userInfo, UserInfoModel.class);
        try {
            // 设置用户角色
            List<DmSysUserRole> roles = systemService.getRolesByUserId(convert.getUserId());
            List<String> roleIds = roles.stream().map(DmSysUserRole::getRoleId).collect(Collectors.toList());
            convert.setRoleIds(roleIds);
            log.info("doLogin.user:{}",convert.toString());
            request.getSession().setAttribute(SessionConstant.SYS_USER,convert);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponResult.failed("用户信息设置异常:"+e.getMessage());
        }
        return ResponResult.success(convert);
    }

    @SysLog("菜单查询")
    @GetMapping("/getMenus")
    public ResponResult getMenus(HttpServletRequest request, HttpServletResponse response) {
        UserInfoModel user = (UserInfoModel) request.getSession().getAttribute(SessionConstant.SYS_USER);
        if (Objects.isNull(user) && needLogin.equals(CommonConstant.NONEED_LOGIN)) {
            user = new UserInfoModel();
            user.setUserId(100200);
            user.setUserName("admin");
            user.setLoginName("admin");
        }
        List<MenuInfoModel> map = systemService.getMenus(user.getUserId());
        return ResponResult.success(map);
    }

    @SysLog("微信回调GET")
    @GetMapping("/weChatCallBack")
    public String weChatCallBack(HttpServletRequest request, WeChatForm form) throws Exception {
        UserUtils.setRequest(request);
        log.info("weChatCallBack.stat...");
        log.info("weChatCallBack.params:{}",form.toString());
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt("Ogfx0EFy","W240BeLf4znLcM7CB5v46CL4jDKJXV6LmlmWrFWvQXg","ww97d4233cae0f70ec");
        log.info("msg_signature:{},timestamp:{},nonce:{},echostr:{},token:{}",form.getMsg_signature(),form.getTimestamp(),form.getNonce(),form.getEchostr(),form.getToken());
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            log.info("key:{},values:{}",key,value);
        }
        // 需要返回的明文
        String sEchoStr = "";
        try {
            sEchoStr = wxcpt.VerifyURL(form.getMsg_signature(), form.getTimestamp(),
                    form.getNonce(), form.getEchostr());
            log.info("verifyurl echostr: " + sEchoStr);
            // 验证URL成功，将sEchoStr返回
            // HttpUtils.SetResponse(sEchoStr);
        } catch (Exception e) {
            //验证URL失败，错误原因请查看异常
            e.printStackTrace();
            log.error("**********error:{}",e.getMessage());
        }
        log.info("RRRRRRRRRRRRRRRRRRRRRRRRRRRR:{}",sEchoStr);
        return sEchoStr;
    }

    @SysLog("微信回调POST")
    @PostMapping("/weChatCallBack")
    public String weChatCallBackPost(HttpServletRequest request, WeChatForm form) throws Exception {
        try {
            UserUtils.setRequest(request);
            log.info("weChatCallBackPost.stat...");
            log.info("weChatCallBackPost.params:{}",form.toString());
            Map<String, String[]> parameterMap = request.getParameterMap();
            Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
            for (Map.Entry<String, String[]> entry : entries) {
                String key = entry.getKey();
                String[] value = entry.getValue();
                log.info("weChatCallBackPost.key:{},values:{}",key,value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @SysLog("微信回调2")
    @RequestMapping("/weChatCallBackTwo")
    public String weChatCallBackTwo(HttpServletRequest request, WeChatForm form) throws Exception {
        UserUtils.setRequest(request);
        log.info("weChatCallBackTwo.stat...");
        log.info("weChatCallBackTwo.params:{}",form.toString());
        WXBizMsgCrypt wxcpt = new WXBizMsgCrypt("Ogfx0EFy","W240BeLf4znLcM7CB5v46CL4jDKJXV6LmlmWrFWvQXg","ww97d4233cae0f70ec");
        log.info("weChatCallBackTwo.msg_signature:{},timestamp:{},nonce:{},echostr:{},token:{}",form.getMsg_signature(),form.getTimestamp(),form.getNonce(),form.getEchostr(),form.getToken());
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
        for (Map.Entry<String, String[]> entry : entries) {
            String key = entry.getKey();
            String[] value = entry.getValue();
            log.info("weChatCallBackTwo.key:{},values:{}",key,value);
        }
        // 需要返回的明文
        String sEchoStr = "";
        try {
            sEchoStr = wxcpt.VerifyURL(form.getMsg_signature(), form.getTimestamp(),
                    form.getNonce(), form.getEchostr());
            log.info("weChatCallBackTwo.verifyurl echostr: " + sEchoStr);
            // 验证URL成功，将sEchoStr返回
            // HttpUtils.SetResponse(sEchoStr);
        } catch (Exception e) {
            //验证URL失败，错误原因请查看异常
            e.printStackTrace();
            log.error("*****weChatCallBackTwo*****error:{}",e.getMessage());
        }
        log.info("weChatCallBackTwo.result:{}",sEchoStr);
        return sEchoStr;
    }


}
