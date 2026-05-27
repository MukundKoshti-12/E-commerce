package com.scaler.paymentservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {
    @GetMapping("/paymentStatus")
    public void updatePaymentStatus() {
        // Update the db of orders
    }
}
