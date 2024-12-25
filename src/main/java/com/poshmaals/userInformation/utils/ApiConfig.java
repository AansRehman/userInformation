package com.poshmaals.userInformation.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "api-config")
@Getter
@Setter
@Component
public class ApiConfig {
    private int maxRetryAttempts;
    private int blockDurationMinutes;
    private int requestWindowSeconds;
    private int otpExpiryTimeMinutes;
}
