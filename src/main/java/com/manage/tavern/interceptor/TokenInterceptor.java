package com.manage.tavern.interceptor;

import com.manage.tavern.constant.CommonConstant;
import com.manage.tavern.constant.SessionConstant;
import com.manage.tavern.po.sys.UserInfoModel;
import com.manage.tavern.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Arrays;

import static com.manage.tavern.utils.TokenUtil.getUser;


public class TokenInterceptor implements HandlerInterceptor {

	@Value("${common.need-login}")
	private Integer needLogin;

	public final static Logger logger = LoggerFactory.getLogger(TokenInterceptor.class);

	/**
	 * 预处理回调方法，实现处理器的预处理（如登录检查），返回值：true表示继续流程（如调用下一个拦截器或处理器），
	 * false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应。
	 * @param req
	 * @param resp
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object obj) throws Exception {
		UserUtils.setRequest(req);
		String requestURL = req.getRequestURL().toString();
		if(needLogin.equals(CommonConstant.NONEED_LOGIN)){
			logger.info("无需登录...NO_LOGIN...");
			UserInfoModel user = getTestUser();
			req.getSession().setAttribute(SessionConstant.SYS_USER,user);
			return true;
		}
		//检查是否登录
		if(!validLogin()){
			resp.sendRedirect("/#/login");
			return false;
		}
		return true;
	}

	/**
	 * 开发环境无登录状态时模拟登录
	 * @return
	 */
	private UserInfoModel getTestUser() {
		UserInfoModel user = new UserInfoModel();
		user.setUserId(100200);
		user.setUserName("管理员");
		user.setLoginName("root");
		user.setRoleIds(Arrays.asList("ROLE_TAVERN_ROOT"));
		return user;
	}

	/**
	 * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），
	 * 此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，modelAndView也可能为null。
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @throws Exception
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	/**
	 * 整个请求处理完毕回调方法，即在视图渲染完毕时回调。该方法也是需要当前对应的Interceptor 的preHandle方法的返回值
	 * 为true时才会执行。这个方法的主要作用是用于进行资源清理工作的，如性能监控中我们可以在此记录结束时间并输出消耗时间。
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @throws Exception
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		UserUtils.removeRequest();
	}

	/**
	 * 验证是否需要登录
	 */
	private boolean validLogin(){
		if(needLogin.equals(CommonConstant.NONEED_LOGIN)){
			return true;
		}
		UserInfoModel sessionUser = getUser();
		if(null == sessionUser){
			return false;
		}
		return true;
	}

}
