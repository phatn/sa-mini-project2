package edu.miu.bankservice.entity;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class PaymentDto {
    private String type;
    private String accountEmail;
    private String firstName;
    private String lastName;
    private String zipCode;
    private String street;
    private String city;
    private Map<String, String> paymentMap;
}
