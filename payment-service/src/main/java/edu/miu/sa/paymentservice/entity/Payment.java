package edu.miu.sa.paymentservice.entity;


import lombok.Data;

import java.util.Map;

@Data
public class Payment {

    private PaymentType type;

    private Map<String, String> paymentMap;

}
