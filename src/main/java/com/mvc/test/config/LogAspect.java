package com.mvc.test.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 切面
 *
 * @author JunWu
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    public static final String SUCCESS = "成功";
    public static final String FAIL = "异常";


    /**
     * 也可以拦截指定注解
     */
    @Pointcut("execution(public * com.mvc.test.controller..*(..))")
    public void pointcut() {
    }

    /**
     * @param jp
     * @param ex
     * @Before(value = "pointcut()")
     * public void before() {
     * log.info("before");
     * }
     * @After(value = "pointcut()")
     * public void after() {
     * log.info("after");
     * }
     * @AfterReturning("pointcut()") public void afterReturning() {
     * log.info("afterReturning");
     * }
     * @AfterReturning(value = "pointcut()" throu)
     * public void afterReturning(PoinJoint pt , Exception ex) {
     * log.info("afterReturning");
     * }
     * 注意:
     * 异常通知必须添加JoinPoint
     */
    @AfterThrowing(throwing = "ex", value = "pointcut()")
    public void afterThrowing(JoinPoint jp, Exception ex) {
        log.error("{}", ex.getMessage());
    }

    @Around(value = "pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = null;
        long costTime = 0;
        Throwable ex = null;
        try {
            long timeMillis = System.currentTimeMillis();
            signature = joinPoint.getSignature();
            Object proceed = joinPoint.proceed();
            costTime = System.currentTimeMillis() - timeMillis;
            return proceed;
        } catch (Throwable e) {
            ex = e;
            throw e;
        } finally {
            String methodName = signature.getName();
            String className = signature.getDeclaringType().getName();
            Object[] args = joinPoint.getArgs();
            log.info("请求{},消耗时间:{}ms,请求路径:{},参数:{}", costTime == 0 ? FAIL : SUCCESS, costTime, className + "." + methodName, args);
            if (ex != null) {
                log.error("{}", ex.getMessage());
            }
        }
    }
}
