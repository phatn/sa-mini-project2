package edu.miu.sa.paymentservice.service;

import edu.miu.sa.paymentservice.entity.Account;
import edu.miu.sa.paymentservice.entity.Address;
import edu.miu.sa.paymentservice.entity.Payment;
import edu.miu.sa.paymentservice.entity.PaymentShip;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final RestTemplate restTemplate;

    private final Environment env;

    @Value("${account-service-secret-key}")
    private String accountSecretKey;

    @Value("${account-service-url}")
    private String accountServiceUrl;

    public PaymentServiceImpl(RestTemplate restTemplate, Environment env) {
        this.restTemplate = restTemplate;
        this.env = env;
    }

    @Override
    public void pay(Payment payment) {
        HttpHeaders headersProduct = new HttpHeaders();
        headersProduct.set("X-ACCOUNT-SERVICE-KEY", accountSecretKey);
        headersProduct.set("Content-Type", "application/json");
        String urlAccount = accountServiceUrl + "?email=" + payment.getAccountEmail();
        Address addressShipping = null;
        Account accountShipping = null;
        try {
            HttpEntity<Account> httpEntity = new HttpEntity<>(headersProduct);
            accountShipping = restTemplate.exchange(urlAccount, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Account>(){}).getBody();
            log.info("Response from Account Service: " + accountShipping);
            for (Address address : accountShipping.getAddress()) {
                if (address.getId() == accountShipping.getPreferredAddress()) {
                    addressShipping = address;
                }
            }
            log.info("Address shipping: " + addressShipping);
        } catch (Exception e) {
            throw new RuntimeException("Error to send request to payment service!" + e.getMessage());
        }

        String url = env.getProperty("payment." + payment.getType().toLowerCase() + "-service-url");
        String secretKey = env.getProperty(payment.getType().toLowerCase() + "-service-secret-key");
        String headerKey = "X-" + payment.getType().toUpperCase() + "-SERVICE-KEY";
        System.out.println("URL: " + url);

        // Send request to Payment method
        HttpHeaders headers = new HttpHeaders();
        headers.set(headerKey, secretKey);
        headers.set("Content-Type", "application/json");
        PaymentShip paymentShip = PaymentShip.builder()
                .firstName(accountShipping.getFirstName())
                .lastName(accountShipping.getLastName())
                .zipCode(addressShipping.getZipCode())
                .street(addressShipping.getStreet())
                .city(addressShipping.getCity())
                .paymentMap(payment.getPaymentMap())
                .build();

        try {
            HttpEntity<PaymentShip> requestEntity = new HttpEntity<>(paymentShip, headers);
            String response = restTemplate.postForEntity(url, requestEntity, String.class).getBody();
            log.info("Response from Payment method Service: " + response);
        } catch (Exception e) {
            throw new RuntimeException("Error to send request to payment service!" + e.getMessage());
        }
    }
}
