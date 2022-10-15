package edu.miu.sa.paymentservice.dto;

import lombok.Data;

@Data
public class OrderResponse {
    private PaymentDTO paymentMethod;
    private AddressDTO address;
    private String orderNumber;
    private Double totalAmount;
}
