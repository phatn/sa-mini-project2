package edu.miu.sa.paymentservice.entity;


import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Payment {

    private String type;

    private String accountEmail;

    private Map<String, String> paymentMap;

}
