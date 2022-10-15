package edu.miu.sa.paymentservice.service;

import edu.miu.sa.paymentservice.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {
    private final RestTemplate restTemplate;

    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("paypal.paypal-url")
    private String paypalUrl;

    @Value("bank.bank-url")
    private String bankUrl;

    @Value("credit.credit-url")
    private String creditUrl;

    public void failedPayment(String orderNumber, String reason) {
    }
    public void decidePayment(OrderResponse orderResponse) {
        switch (orderResponse.getPaymentMethod().getType()) {
            case "PAYPAL":
                checkoutPaypal(orderResponse);
                break;
            case "BANK":
                checkoutBank(orderResponse);
                break;
            default:
                checkoutCredit(orderResponse);
                break;
        }
    }

    private void checkoutPaypal(OrderResponse orderResponse) {

    }

    private void checkoutBank(OrderResponse orderResponse) {

    }

    private void checkoutCredit(OrderResponse orderResponse) {

    }
}
