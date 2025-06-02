package com.luv2code.doan.controller;


import com.luv2code.doan.service.PayPalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/paypal")
public class PayPalController {

    private final PayPalService payPalService;

    public PayPalController(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestParam String amount) {
        Map<String, Object> response = payPalService.createOrder(amount);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/success")
    public ResponseEntity<?> captureOrder(@RequestParam String token) {
        Map<String, Object> response = payPalService.captureOrder(token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cancel")
    public String cancel() {
        return "Payment canceled by user.";
    }

}
