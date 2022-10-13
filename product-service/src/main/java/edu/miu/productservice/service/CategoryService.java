package edu.miu.productservice.service;

import edu.miu.productservice.dto.CategoryDto;

import java.util.Set;

public interface CategoryService {
    Set<CategoryDto> findAll();
    CategoryDto findById(int id);
}
