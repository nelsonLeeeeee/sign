package com.nelson.sign.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SignAspect  {

    private final static Logger logger = LoggerFactory.getLogger(SignAspect.class);

    @Pointcut("execution(public * com.nelson.sign.controller.*.*(..))")
    public void log(){}

    @Before("log()")
    public void isLogin(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("url={}",request.getRequestURL());
        logger.info("method={}",request.getMethod());
        logger.info("ip={}",request.getRemoteAddr());
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() +"."+joinPoint.getSignature().getName());
        logger.info("args={}",joinPoint.getArgs());

        if(request.getParameter("uid")==null){
            logger.info("需要先登录");
        }
        System.out.println("OK");
    }

    @After("log()")
    public void doAfter(){

    }

    @AfterReturning(returning = "object",pointcut = "log()")
    public  void doAfterReturning(Object object){
        logger.info("response={}",object.toString());
    }

}
