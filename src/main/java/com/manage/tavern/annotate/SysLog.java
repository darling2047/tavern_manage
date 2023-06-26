package com.manage.tavern.annotate;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author lincheng
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String value() default "";
}
