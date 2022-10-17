package edu.miu.sa.paymentservice.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Order {
    private String type;

    private String accountEmail;

    private Map<String, String> paymentMap;

    private String firstName;
    private String lastName;
    private String zipCode;
    private String street;
    private String city;
}
