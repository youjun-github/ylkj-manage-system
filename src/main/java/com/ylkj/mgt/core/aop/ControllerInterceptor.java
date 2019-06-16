package com.ylkj.mgt.core.aop;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * controller统一拦截器
 *
 * @author youjun
 * @create 2019-06-16 18:00
 */
@Aspect
@Component
public class ControllerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerInterceptor.class);

    private String name;

    @Autowired
    private GlobalExceptionHandler globalExceptionHandler;

    /**
     * 拦截所有controller请求
     */
    @Pointcut("execution(* com.ylkj.mgt.web.controller..*(..))")
    private void controllerMethodPointcut() {
    }

    @Around("controllerMethodPointcut()")
    public Object interceptor(final ProceedingJoinPoint pjp) {

        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            result = globalExceptionHandler.handleThrowable(e);
        }

        return result;
    }

    @Before(value = "controllerMethodPointcut()")
    public void printParam(JoinPoint point) {
        Object[] args = point.getArgs();
        Signature method = point.getSignature();
        Object target = point.getTarget();
        name = String.format("%s#%s", target.getClass().getName(), method.getName());
        String string = null;
        if (args.length != 0) {
            string = JSONObject.toJSONString(args[0]);
        }
        LOGGER.info(String.format("%s请求参数：%s", name, string));
//      LOGGER.info(String.format("%s参数：%s", name,string.length() >10000?string.substring(0, 10000):string));
    }

    @AfterReturning(pointcut = "controllerMethodPointcut()", returning = "result")
    public void printResult(JoinPoint point, Object result) {
        if (result instanceof Mono) {
            Mono.from((Mono) result).subscribe(x -> print(x));
        } else {
            print(result);
        }
    }

    private void print(Object result) {
        String str = "";
        if ((str = JSONObject.toJSONString(result)).length() > 1000)
            str = str.substring(0, 1000) + "...";
        LOGGER.info(String.format("%s返回结果：%s", name, str));
    }

}
