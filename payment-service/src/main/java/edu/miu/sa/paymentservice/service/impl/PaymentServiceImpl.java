package edu.miu.sa.paymentservice.service.impl;

import edu.miu.sa.paymentservice.client.AccountClient;
import edu.miu.sa.paymentservice.client.BankClient;
import edu.miu.sa.paymentservice.client.CreditClient;
import edu.miu.sa.paymentservice.client.PaypalClient;
import edu.miu.sa.paymentservice.entity.PaymentMethod;
import edu.miu.sa.paymentservice.entity.PaymentRequest;
import edu.miu.sa.paymentservice.service.PaymentService;

public class PaymentServiceImpl implements PaymentService {

    AccountClient accountClient;
    PaypalClient paypalClient;
    BankClient bankClient;
    CreditClient creditClient;

    @Override
    public PaymentMethod getInfoPayment() {
        return accountClient.getPaymentMethod();
    }

    @Override
    public void decidePayment(PaymentRequest paymentRequest) {
        switch (paymentRequest.getPaymentMethod().getType()) {
            case "PAYPAL":
                paypalClient.checkout(paymentRequest);
                break;
            case "BANK":
                bankClient.checkout(paymentRequest);
                break;
            case "CREDIT CARD":
                creditClient.checkout(paymentRequest);
                break;
            default:
                break;
        }
    }

    @Override
    public void failedPayment(String orderNumber, String reason) {

    }
}
