package edu.miu.productservice.service.impl;

import edu.miu.productservice.dto.ProductDto;
import edu.miu.productservice.entity.Product;
import edu.miu.productservice.mapper.ProductMapper;
import edu.miu.productservice.repository.ProductRepository;
import edu.miu.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Value("${product-quantity-threshold}")
    private int productQuantityThreshold;

    @Override
    public ProductDto findById(int id) {
        ProductDto product = productRepository.findById(id)
                .map(productMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Cannot find product: " + id));
        return product;
    }

    @Override
    public Set<ProductDto> findProductByIds(List<Integer> ids) {
        return productMapper.toDtos(productRepository.findProductByIds(ids));
    }

    @Override
    @Transactional
    public void updateQuantity(List<ProductDto> productDtos) {
        List<Integer> ids = productDtos.stream().map(ProductDto::getId).toList();
        Set<Product> products = productRepository.findProductByIds(ids);
        for(Product product : products) {
            for(ProductDto productDto : productDtos) {
                if(product.getId() == productDto.getId()) {
                    product.setQuantity(product.getQuantity() - productDto.getQuantity());
                    if(product.getQuantity() < productQuantityThreshold) {
                        log.warn("Product quantity of {} is lower than {}", product.getId(), productQuantityThreshold);
                    }
                }
            }
        }
        productRepository.saveAll(products);
    }

    @Override
    public Set<ProductDto> findAll() {
        Set<Product> products = new HashSet<>();
        productRepository.findAll().forEach(products::add);
        return productMapper.toDtos(products);
    }
}
