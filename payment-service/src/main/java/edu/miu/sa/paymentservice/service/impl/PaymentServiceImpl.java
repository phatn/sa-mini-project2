package edu.miu.sa.paymentservice.service.impl;

import edu.miu.sa.paymentservice.entity.Payment;
import edu.miu.sa.paymentservice.service.PaymentService;
import org.springframework.web.client.RestTemplate;

public class PaymentServiceImpl implements PaymentService {
    @Override
    public Payment getInfoPayment() {
        String uri = "http://localhost:8080/order/payment";
        RestTemplate restTemplate = new RestTemplate();
        Payment payment = restTemplate.getForObject(uri, Payment.class);
        return payment;
    }
}
