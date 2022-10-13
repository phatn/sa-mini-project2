package edu.miu.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ProductDto {
    private int id;
    private int quantity;
    private String name;
    private String vendor;
    private double price;
}
