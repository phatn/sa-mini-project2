package edu.miu.creditservice.dto;

import lombok.Data;

@Data
public class PaymentRequestDTO {
    private PaymentDTO paymentMethod;
    private AddressDTO address;
    private String orderNumber;
    private Double totalAmount;
}
