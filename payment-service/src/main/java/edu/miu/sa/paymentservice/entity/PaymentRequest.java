package edu.miu.sa.paymentservice.entity;

import lombok.Data;

@Data
public class PaymentRequest {
    private PaymentMethod paymentMethod;
    private String orderNumber;
    private Double totalPrices;
}
