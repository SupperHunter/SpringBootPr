package com.luv2code.doan.configuration;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "paypal")
@Getter
@Setter
public class PayPalConfig {
    private String clientId;
    private String clientSecret;
    private String apiBaseUrl;
}
