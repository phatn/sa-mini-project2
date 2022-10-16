package edu.miu.sa.paymentservice.service;

import edu.miu.sa.paymentservice.entity.Payment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final RestTemplate restTemplate;

    private Environment env;

    public PaymentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void pay(Payment payment) {
        String url = env.getProperty(payment.getType().toString().toLowerCase() + "_url");
        System.out.println("URL: " + url);
    }
}
