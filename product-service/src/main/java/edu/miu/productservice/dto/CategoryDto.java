package edu.miu.productservice.dto;

import edu.miu.productservice.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CategoryDto {
    private int id;
    private String name;
    private Set<Product> products;
}
