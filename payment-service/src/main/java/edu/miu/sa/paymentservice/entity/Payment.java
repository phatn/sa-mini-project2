package edu.miu.sa.paymentservice.entity;


import lombok.Data;

import java.util.Map;

@Data
public class Payment {

    private String type;

    private String accountEmail;

    private Map<String, String> paymentMap;

}
