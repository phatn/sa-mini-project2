package edu.miu.sa.orderservice.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private int id;

    private int quantity;

    private String name;

    private String vendor;

    private double price;
}
