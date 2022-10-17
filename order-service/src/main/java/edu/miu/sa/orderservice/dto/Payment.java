package edu.miu.sa.orderservice.dto;


import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class Payment {

    private String accountEmail;

    private String type;

    private Map<String, String> paymentMap;

}
