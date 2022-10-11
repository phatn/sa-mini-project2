package edu.miu.productservice.dto;

import edu.miu.productservice.entity.ProductType;
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
    private String name;
    private String vendor;
    private ProductType category;
}
