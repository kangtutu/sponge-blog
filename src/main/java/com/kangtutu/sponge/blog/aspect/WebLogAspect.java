package com.kangtutu.sponge.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {

    private static final Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.kangtutu.sponge.blog.controller..*.*(..))")//切入点描述，此为controller包的切入点
    public void controllerLog(){} //签名，可理解为该切入点的名称

    @Pointcut("execution(public * com.kangtutu.sponge.blog.service..*.*(..))")//切入点描述，此为service包的切入点
    public void serviceLog(){} //签名，可理解为该切入点的名称

    @Before("controllerLog()")//前置通知，再切入点方法之前执行的相关内容
    public void logBeforeController(JoinPoint joinPoint){
        // RequestContextHolder 是 SpringMVC 提供的来获取请求的对象
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 获取请求对象
        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        //下面这个getSignature().getDeclaringTypeName()是获取包+类名的   然后后面的joinPoint.getSignature.getName()获取了方法名
        String class_method = joinPoint.getSignature().getDeclaringTypeName()+joinPoint.getSignature().getName();
        //记录请求的信息
        log.info("请求IP:{},请求URL:{},请求方式:{},请求全路径方法名:{},请求参数:{}",request.getRemoteAddr(),request.getRequestURL(),request.getMethod(),class_method,Arrays.toString(joinPoint.getArgs()));
    }

}
