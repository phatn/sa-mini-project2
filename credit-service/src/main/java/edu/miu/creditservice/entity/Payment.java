package edu.miu.creditservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private int id;
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
