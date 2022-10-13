package edu.miu.productservice.service.impl;

import edu.miu.productservice.dto.CategoryDto;
import edu.miu.productservice.entity.Category;
import edu.miu.productservice.mapper.CategoryMapper;
import edu.miu.productservice.repository.CategoryRepository;
import edu.miu.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto findById(int id) {
        CategoryDto category = categoryRepository.findById(id)
                .map(categoryMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Cannot find category: " + id));
        return category;
    }

    @Override
    public Set<CategoryDto> findAll() {
        Set<Category> products = new HashSet<>();
        categoryRepository.findAll().forEach(products::add);
        return categoryMapper.toDtos(products);
    }
}
