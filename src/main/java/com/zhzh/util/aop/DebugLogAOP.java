package com.zhzh.util.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Version:     1.0.0.0
 * Description: TO DO
 */
@Aspect
public class DebugLogAOP {


    Logger logger = LoggerFactory.getLogger(DebugLogAOP.class);

    private static final String EXECUTION = "(execution(* com.zhzh..*.*(..)))" +
            "and !execution(* com.zhzh..*Mapper.*(..))";


    @Around(EXECUTION)
    public Object debugLog(ProceedingJoinPoint pjp) throws Throwable {

        String classAndMethod = pjp.getSignature().toShortString();

        Object o = null;
        try {
            Object[] params = pjp.getArgs();
            for (Object param : params) {

                if (param != null) {
                    logger.debug("[{}]:input param:{}", classAndMethod, param.toString());
                }
            }
            o = pjp.proceed();

            if (o != null && o instanceof Void) {
                logger.debug("[{}]:output param:void", classAndMethod);
            } else {
                logger.debug("[{}]:output param:{}", classAndMethod, Objects.toString(o, ""));
            }

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }
        return o;
    }
}
