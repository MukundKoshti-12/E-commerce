package com.scaler.paymentservice.services;

import com.scaler.paymentservice.paymentgateways.PaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private PaymentGateway paymentGateway;

    PaymentServiceImpl(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public String generatePaymentLink(Long orderId) throws StripeException {
        return paymentGateway.generatePaymentLink(orderId);
    }
}
