package edu.miu.productservice.controller;

import edu.miu.productservice.dto.CategoryDto;
import edu.miu.productservice.dto.ProductDto;
import edu.miu.productservice.service.CategoryService;
import edu.miu.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public Set<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public CategoryDto findById(@PathVariable int id) {
        return categoryService.findById(id);
    }
}
