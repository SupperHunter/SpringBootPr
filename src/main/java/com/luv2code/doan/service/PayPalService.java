package com.luv2code.doan.service;


import com.luv2code.doan.configuration.PayPalConfig;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class PayPalService {


    private final PayPalConfig config;
    private final RestTemplate restTemplate;

    public PayPalService(PayPalConfig config) {
        this.config = config;
        this.restTemplate = new RestTemplate();
    }


    public String getAccessToken() {
        String url = config.getApiBaseUrl() + "/v1/oauth2/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(config.getClientId(), config.getClientSecret());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        return response.getBody().get("access_token").toString();
    }


    public Map<String, Object> createOrder(String amount) {
        String url = config.getApiBaseUrl() + "/v2/checkout/orders";
        String token = getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        body.put("intent", "CAPTURE");

        Map<String, Object> amountObj = Map.of(
                "currency_code", "USD",
                "value", amount
        );

        Map<String, Object> purchaseUnit = Map.of(
                "amount", amountObj
        );

        body.put("purchase_units", List.of(purchaseUnit));

        Map<String, Object> applicationContext = Map.of(
                "return_url", "http://localhost:8080/api/paypal/success",
                "cancel_url", "http://localhost:8080/api/paypal/cancel"
        );
        body.put("application_context", applicationContext);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        return response.getBody();
    }

    public Map<String, Object> captureOrder(String orderId) {
        String url = config.getApiBaseUrl() + "/v2/checkout/orders/" + orderId + "/capture";
        String token = getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);

        return response.getBody();
    }

}
