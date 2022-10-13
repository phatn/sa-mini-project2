package edu.miu.productservice.mapper;

import edu.miu.productservice.dto.CategoryDto;
import edu.miu.productservice.entity.Category;
import edu.miu.productservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final ModelMapper mapper;

    public CategoryDto toDto(Category category) {
        return mapper.map(category, CategoryDto.class);
    }

    public Set<CategoryDto> toDtos(Set<Category> category) {
        return category.stream().map(this::toDto).collect(Collectors.toSet());
    }
}
