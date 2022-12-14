package edu.miu.shipmentservice.entity;

import lombok.Data;

import java.util.Map;

@Data
public class Payment {
    private String firstName;
    private String lastName;
    private String zipCode;
    private String street;
    private String city;
    private Map<String, String> paymentMap;
}
