package edu.miu.sa.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Product {

    private int id;

    private int quantity;

    private String name;

    private String vendor;

    private double price;
}
