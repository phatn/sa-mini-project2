package edu.miu.productservice.service;

import edu.miu.productservice.dto.ProductDto;

import java.util.Set;

public interface ProductService {
    Set<ProductDto> findAll();
    ProductDto findById(int id);
}
