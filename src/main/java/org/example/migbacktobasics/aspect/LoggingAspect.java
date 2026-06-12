package org.example.migbacktobasics.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("@annotation(loggable)")
    public Object logMethodInfo(ProceedingJoinPoint joinPoint, Loggable loggable) throws Throwable {

        var methodName = joinPoint.getSignature().toShortString();
        LOGGER.info("ASPECT - Entering {} with args: {}", methodName, Arrays.toString(joinPoint.getArgs()));
        var startTime = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();
            LOGGER.info("ASPECT - Exiting {} with result: {}", methodName, result);
            return result;
        } catch (Exception e) {
            LOGGER.error("ASPECT - Exception in {}: {}", methodName, e.getMessage());
            throw e;
        } finally {
            var duration = System.currentTimeMillis() - startTime;
            LOGGER.info("ASPECT - {} executed in {} ms", methodName, duration);
        }

    }

}
