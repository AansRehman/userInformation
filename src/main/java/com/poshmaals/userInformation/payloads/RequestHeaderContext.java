package com.poshmaals.userInformation.payloads;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class RequestHeaderContext {
    private String reqIP;
    private String token;
    private String authHeader;
    private String userAgent;
    private String useCaseName;
    private String identity;
    private String version;
    private String apiName;
}
