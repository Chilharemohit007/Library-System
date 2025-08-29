package com.librarySystem.LibrarySystem.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    // Pointcut for all methods inside service package
    @Pointcut("execution(* com.librarySystem.LibrarySystem.service.*.*(..))")
    public void serviceMethods() {}

    // Before Advice
    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before Method: " + joinPoint.getSignature().getName());
        System.out.println("   Arguments: " + Arrays.toString(joinPoint.getArgs()));
    }

    // After Advice
    @After("serviceMethods()")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("After Method: " + joinPoint.getSignature().getName());
    }

    // AfterReturning Advice
    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("Method Returned: " + result);
    }

    // Around Advice (measure execution time)
    @Around("serviceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed(); // execute target method
        long executionTime = System.currentTimeMillis() - start;
        System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");
        return proceed;
    }

    // AfterThrowing Advice (log exception + args)
    @AfterThrowing(pointcut = "serviceMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Exception ex) {
        System.err.println("❌ Exception in method: " + joinPoint.getSignature().getName());
        System.err.println("   Arguments: " + Arrays.toString(joinPoint.getArgs()));
        System.err.println("   Cause: " + ex.getMessage());
    }
}
