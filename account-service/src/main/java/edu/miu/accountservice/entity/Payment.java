package edu.miu.accountservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @ManyToOne
    @JsonBackReference
    private Account owner;
}
