package com.contest.ichapp.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP拦截打印请求地址、参数
 *
 * @author 韦秉芮
 * @version 1.0
 */
@Aspect
@Component
@Slf4j
public class LoggerByAop {
    //设置切面点
    @Pointcut(value = "(execution(* com.contest.ichapp.controller.*.*(..)))")
    public void webLog() {
    }

    //指定切点前的处理方法
    @Before("webLog()")
    public void doBefore() {
        //获取request对象
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        //拼接请求内容
        log.info("RequestURL: " + request.getRequestURL().toString() + " " + request.getMethod());
    }
}
