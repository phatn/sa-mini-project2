package edu.miu.accountservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Embeddable
@Getter
@Setter
public class Payment {
    private String type;
//    // for card
//    private String cardNumber;
//    private String cardSecurityCode;
//    private String cardExpires;
//    // for bank
//    private String bankAccount;
//    private String routingNumber;
//    private String bankName;
//    // for paypal
//    private String accountNumber;
//    private String accountToken;
}
