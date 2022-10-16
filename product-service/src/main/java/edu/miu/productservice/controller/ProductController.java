package edu.miu.productservice.controller;

import edu.miu.productservice.dto.ProductDto;
import edu.miu.productservice.exception.AuthenticationException;
import edu.miu.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class ProductController {

    @Value("${product-service-secret-key}")
    private String secretKey;

    private final HttpServletRequest request;

    private final ProductService productService;

    @GetMapping("/api/products")
    public Set<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/api/products/{id}")
    public ProductDto findById(@PathVariable int id) {
        return productService.findById(id);
    }

    @GetMapping("/service/products")
    public Set<ProductDto> findProductByIds(@RequestParam List<Integer> ids) {

        if(!secretKey.equals(request.getHeader("X-PRODUCT-SERVICE-KEY"))) {
            throw new  AuthenticationException("Authentication failed");
        }
        return productService.findProductByIds(ids);
    }
}
