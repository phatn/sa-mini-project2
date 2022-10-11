package edu.miu.productservice.service.impl;

import edu.miu.productservice.dto.ProductDto;
import edu.miu.productservice.entity.Product;
import edu.miu.productservice.mapper.ProductMapper;
import edu.miu.productservice.repository.ProductRepository;
import edu.miu.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductDto findById(int id) {
        ProductDto product = productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Cannot find product: " + id));
        return product;
    }

    @Override
    public Set<ProductDto> findAll() {
        Set<Product> products = new HashSet<>();
        productRepository.findAll().forEach(products::add);
        return productMapper.toDtos(products);
    }
}
