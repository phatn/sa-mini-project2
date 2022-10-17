package edu.miu.bankservice.service;

import edu.miu.bankservice.entity.Account;
import edu.miu.bankservice.entity.Payment;
import edu.miu.bankservice.entity.PaymentDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class BankService {
    @Autowired
    private final RestTemplate restTemplate;

    private Environment env;

    @Value("${shipping-service-secret-key}")
    private String secretKey;

    @Value("${shipping-service-url}")
    private String shippingServiceUrl;

    @Value("${account-service-secret-key}")
    private String accountSecretKey;

    @Value("${account-service-url}")
    private String accountServiceUrl;

    public BankService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void shipToAddress(PaymentDto paymentDto) {
        if (paymentDto.getPaymentMap() == null) {
            // Get info from account
            HttpHeaders headersAccount = new HttpHeaders();
            headersAccount.set("X-ACCOUNT-SERVICE-KEY", accountSecretKey);
            headersAccount.set("Content-Type", "application/json");
            String urlAccount = accountServiceUrl + "?email=" + paymentDto.getAccountEmail();
            Payment paymentInfo = null;
            Account accountShipping = null;
            try {
                HttpEntity<Account> httpAccount = new HttpEntity<>(headersAccount);
                accountShipping = restTemplate.exchange(urlAccount, HttpMethod.GET, httpAccount, new ParameterizedTypeReference<Account>(){}).getBody();
                log.info("PAYMENT account: " + accountShipping);

                for (Payment payment : accountShipping.getPayment()) {
                    if (payment.getId() == accountShipping.getPreferredPayment()) {
                        paymentInfo = payment;
                    }
                }
                log.info("PAYMENT payment info: " + paymentInfo);
                Map<String, String> paymentMap = new HashMap<>();
                paymentMap.put("bankAccount", paymentInfo.getBankAccount());
                paymentMap.put("bankRouting", paymentInfo.getBankRouting());
                paymentMap.put("bankName", paymentInfo.getBankName());
                paymentDto.setPaymentMap(paymentMap);
            } catch (Exception e) {
                throw new RuntimeException("Error to send request to payment service!" + e.getMessage());
            }
        }
        HttpHeaders headers = createHeaders();
        HttpEntity<PaymentDto> httpEntity = new HttpEntity<>(paymentDto, headers);
        String response = restTemplate.postForEntity(shippingServiceUrl, httpEntity, String.class).getBody();
        log.info(response);
    }

    private HttpHeaders createHeaders(){
        return new HttpHeaders() {{
            set( "X-SHIPPING-SERVICE-KEY", secretKey);
            set("Content-Type", "application/json");
        }};
    }
}
