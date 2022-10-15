package edu.miu.sa.paymentservice.dto;

import lombok.Data;

@Data
public class PaymentDTO {
    // CREDIT, BANK, PAYPAL
    private String type;

    // CREDIT
    private String cardNumber;
    private String cardSecurityCode;
    private String cardExpires;
    // BANK
    private String bankAccount;
    private String bankRouting;
    private String bankName;
    // PAYPAL
    private String paypalNumber;
    private String paypalToken;
}
