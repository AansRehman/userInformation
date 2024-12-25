package com.poshmaals.userInformation.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Autowired
    private LoggingHelperAspect loggingHelperAspect;

    @Around("execution(* com.poshmaals.userInformation.controllers..*(..))")
    public Object logAroundControllerMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        loggingHelperAspect.logRequestDetails(joinPoint.getArgs(), startTime);
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        loggingHelperAspect.logResponseDetails(result, endTime - startTime);

        return result;
    }

    @Before("within(com.poshmaals.userInformation.controllers..*) || within(com.poshmaals.userInformation.services..*)")
    public void logEntryDetails(JoinPoint joinPoint) {
        loggingHelperAspect.logEntryDetails(joinPoint);
    }

    @AfterReturning("within(com.poshmaals.userInformation.controllers..*) || within(com.poshmaals.userInformation.services..*)")
    public void logExitDetails(JoinPoint joinPoint) {
        loggingHelperAspect.logExitDetails(joinPoint);
    }

    @AfterThrowing(pointcut = "within(com.poshmaals.userInformation.controllers..*) || within(com.poshmaals.userInformation.services..*)", throwing = "exception")
    public void logExceptionDetails(JoinPoint joinPoint, Throwable exception) {
        loggingHelperAspect.logExceptionDetails(joinPoint, exception);
    }


}