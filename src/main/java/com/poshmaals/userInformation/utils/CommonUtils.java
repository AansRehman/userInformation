package com.poshmaals.userInformation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;

@Component
public class CommonUtils {
    private static final Logger logger = LogManager.getLogger(CommonUtils.class);
    private static final SecureRandom secureRandom = new SecureRandom();

    private final ApiConfig apiConfig;


    public CommonUtils(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
    }

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        // Register JavaTimeModule to handle LocalDate, LocalTime, etc.
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.findAndRegisterModules(); // Automatically register other available modules
    }
    public static String convertObjectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            logError("Error converting object to JSON: " + e.getMessage(), e);
            return null;
        }
    }
    public static void logError(String message, Throwable exception) {
        logger.error(message, exception);
    }

    public String generateOtp() {
        // Generate a 6-digit OTP using SecureRandom
        int otp = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(otp);
    }

    // Method to check if the next OTP request is allowed (configurable window)
    public boolean isRequestAllowed(Timestamp lastAttemptAt) {
        if (lastAttemptAt == null) {
            // If no last attempt, assume request is allowed
            return true;
        }

        Instant now = Instant.now();
        Instant lastAttempt = lastAttemptAt.toInstant();
        // Use the configurable request window (in seconds) to check the time difference
        return now.isAfter(lastAttempt.plusSeconds(apiConfig.getRequestWindowSeconds()));
    }
    public static String generateResetCode() {
        int code = secureRandom.nextInt(900000) + 100000;
        return String.format("%05d", code);
    }
}
