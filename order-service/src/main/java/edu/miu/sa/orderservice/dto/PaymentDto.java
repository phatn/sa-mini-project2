package edu.miu.sa.orderservice.dto;


import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class PaymentDto {

    private String accountEmail;

    private String type;

    private Map<String, String> paymentMap;

    private String firstName;
    private String lastName;
    private String zipCode;
    private String street;
    private String city;
}
