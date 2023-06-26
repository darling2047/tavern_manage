package com.manage.tavern.aop;

import com.manage.tavern.annotate.SysLog;
import com.manage.tavern.po.sys.TavernSysLog;
import com.manage.tavern.po.sys.UserInfoModel;
import com.manage.tavern.service.SysLogService;
import com.manage.tavern.utils.HttpContextUtils;
import com.manage.tavern.utils.IPUtils;
import com.manage.tavern.utils.TokenUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * 系统日志，切面处理类
 *
 * @author lincheng
 */
@Aspect
@Component
public class SysLogAspect {

    @Resource
    private SysLogService sysLogService;

    /**
     * 定义Pointcut 切点表达式指向SysLog注解
     */
    @Pointcut("@annotation(com.manage.tavern.annotate.SysLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        TavernSysLog sysLog = new TavernSysLog();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            //注解上的描述
            sysLog.setOperation(syslog.value());
        }

        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = Arrays.toString(args);
            sysLog.setParams(params);
        } catch (Exception e) {

        }

        //获取request
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

        //设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));

        sysLog.setTime(time);
        sysLog.setCreateDate(new Date());
        try {
            UserInfoModel user = TokenUtil.getUser();
            if (Objects.nonNull(user)) {
                sysLog.setUsername(user.getLoginName());
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        //保存操作日志
        sysLogService.save(sysLog);
    }
}
