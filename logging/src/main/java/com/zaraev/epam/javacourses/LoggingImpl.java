package com.zaraev.epam.javacourses;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;


@Aspect
@Slf4j
public class LoggingImpl {

    @Around("@annotation(Logging)")
    public Object logMethodInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        String className = joinPoint.getThis().getClass().getSimpleName().split("\\$")[0];
        String methodName = joinPoint.getSignature().getName();
        String methodArgs = Arrays.toString(joinPoint.getArgs());
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        log.info("Метод " + methodName + " класса " + className + " с входными параметрами " + methodArgs + " выполнен за " + executionTime + "мс");
        return proceed;
    }
    }