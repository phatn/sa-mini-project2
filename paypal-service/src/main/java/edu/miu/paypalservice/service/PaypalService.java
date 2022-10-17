package edu.miu.paypalservice.service;

import edu.miu.paypalservice.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PaypalService {
    @Autowired
    private final RestTemplate restTemplate;

    private Environment env;

    @Value("${shipping-service-secret-key}")
    private String secretKey;

    @Value("${shipping-service-url}")
    private String shippingServiceUrl;

    public PaypalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void shipToAddress(Payment payment) {
        if (payment.getPaymentMap().containsKey("paypalNumber") && payment.getPaymentMap().containsKey("paypalToken")) {
            HttpHeaders headers = createHeaders();
            HttpEntity<Payment> httpEntity = new HttpEntity<>(payment, headers);
            String response = restTemplate.postForEntity(shippingServiceUrl, httpEntity, String.class).getBody();
            log.info(response);
        }
    }

    private HttpHeaders createHeaders(){
        return new HttpHeaders() {{
            set( "X-SHIPPING-SERVICE-KEY", secretKey);
            set("Content-Type", "application/json");
        }};
    }
}
