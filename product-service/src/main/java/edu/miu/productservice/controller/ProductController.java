package edu.miu.productservice.controller;

import edu.miu.productservice.dto.ProductDto;
import edu.miu.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Set<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable int id) {
        return productService.findById(id);
    }
}
