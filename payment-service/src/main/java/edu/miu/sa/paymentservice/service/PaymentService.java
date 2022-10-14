package edu.miu.sa.paymentservice.service;

import edu.miu.sa.paymentservice.entity.PaymentMethod;
import edu.miu.sa.paymentservice.entity.PaymentRequest;

public interface PaymentService {
    PaymentMethod getInfoPayment();

    void decidePayment(PaymentRequest paymentRequest);
    void failedPayment(String orderNumber, String reason);
}
