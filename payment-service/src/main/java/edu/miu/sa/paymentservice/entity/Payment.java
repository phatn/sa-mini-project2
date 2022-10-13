package edu.miu.sa.paymentservice.entity;

public class Payment {
    private int totalPrice;

    // CREDIT CARD, BANK, PAYPAL
    private String type;

    // CREDIT CARD
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
