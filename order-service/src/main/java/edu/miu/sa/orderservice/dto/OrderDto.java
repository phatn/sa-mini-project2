package edu.miu.sa.orderservice.dto;

import edu.miu.sa.orderservice.entity.OrderItem;
import lombok.Data;

import java.util.Map;
import java.util.Set;

@Data
public class OrderDto {

    private String accountEmail;

    private String paymentType;

    private Set<OrderItem> orderItems;

    private Map<String, String> payment;
}
