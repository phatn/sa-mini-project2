package edu.miu.sa.paymentservice.entity;

import lombok.Data;

@Data
public class Address {

    private String zipCode;

    private String street;

    private String city;
}