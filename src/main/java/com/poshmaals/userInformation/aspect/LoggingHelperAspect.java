package com.poshmaals.userInformation.aspect;



import com.poshmaals.userInformation.payloads.RequestHeaderContext;
import com.poshmaals.userInformation.utils.CommonUtils;
import com.poshmaals.userInformation.utils.constants.LoggingConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class LoggingHelperAspect {

    private static final Logger logger = LogManager.getLogger(LoggingHelperAspect.class);

    @Autowired
    private RequestHeaderContext requestContext;

    public void logRequestDetails(Object[] args, long startTime) {
        // Log request details in a single line
        logger.info(LoggingConstants.TOKEN_PREFIX + requestContext.getToken() + " ## REQUEST-TIME: " + new Date(startTime));
        logger.info(LoggingConstants.TOKEN_PREFIX + requestContext.getToken() + " ## RequestJSON: " + CommonUtils.convertObjectToJson(args));
    }

    public void logResponseDetails(Object result, long duration) {
        // Log response details in a single line
        logger.info(LoggingConstants.TOKEN_PREFIX + requestContext.getToken() + " ## DURATION: " + duration + "ms");
        logger.info(LoggingConstants.TOKEN_PREFIX + requestContext.getToken() + " ## ResponseJSON: " + CommonUtils.convertObjectToJson(result));
    }
    public void logExceptionResponseDetails(Object result, long duration) {
        // Log response details in a single line
        logger.info(LoggingConstants.TOKEN_PREFIX + requestContext.getToken() + " ## DURATION: " + duration + "ms");
        logger.info(LoggingConstants.TOKEN_PREFIX + requestContext.getToken() + " ## ResponseJSON: " + result);
    }


    public void logAdditionalDetails() {
        logger.info(LoggingConstants.TOKEN_PREFIX + requestContext.getToken() + " ## Requested-Endpoint : " + requestContext.getApiName());
        logger.info(LoggingConstants.TOKEN_PREFIX + requestContext.getToken() + " ## UseCase Name: " + requestContext.getUseCaseName());
        logger.info(LoggingConstants.TOKEN_PREFIX + requestContext.getToken() + " ## Identity: " + requestContext.getIdentity());
        logger.info(LoggingConstants.TOKEN_PREFIX + requestContext.getToken() + " ## Version: " + requestContext.getVersion());
    }

    public void logEntryDetails(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        logger.info("## Landed in  " + className);
    }

    public void logExitDetails(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        logger.info("## Exiting from " + className);
    }

    public void logExceptionDetails(JoinPoint joinPoint, Throwable exception) {
        String className = joinPoint.getSignature().getDeclaringType().getSimpleName();
        logger.error(LoggingConstants.TOKEN_PREFIX + requestContext.getToken() + "  ## Exception in " + className + ": " + exception.getMessage(), exception);
    }
}

