package edu.miu.sa.paymentservice.service;

import edu.miu.sa.paymentservice.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final RestTemplate restTemplate;

    private final Environment env;

    public PaymentServiceImpl(RestTemplate restTemplate, Environment env) {
        this.restTemplate = restTemplate;
        this.env = env;
    }

    @Override
    public void pay(Order order) {
        String url = env.getProperty("payment." + order.getType().toLowerCase() + "-service-url");
        String secretKey = env.getProperty(order.getType().toLowerCase() + "-service-secret-key");
        String headerKey = "X-" + order.getType().toUpperCase() + "-SERVICE-KEY";
        System.out.println("URL: " + url);

        // Send request to Payment method
        HttpHeaders headers = new HttpHeaders();
        headers.set(headerKey, secretKey);
        headers.set("Content-Type", "application/json");
        PaymentDto paymentDto = PaymentDto.builder()
                .firstName(order.getFirstName())
                .lastName(order.getLastName())
                .zipCode(order.getZipCode())
                .street(order.getStreet())
                .city(order.getCity())
                .paymentMap(order.getPaymentMap())
                .accountEmail(order.getAccountEmail())
                .type(order.getType())
                .build();

        try {
            HttpEntity<PaymentDto> requestEntity = new HttpEntity<>(paymentDto, headers);
            String response = restTemplate.postForEntity(url, requestEntity, String.class).getBody();
            log.info("Response from Payment method Service: " + response);
        } catch (Exception e) {
            throw new RuntimeException("Error to send request to payment service!" + e.getMessage());
        }
    }
}
