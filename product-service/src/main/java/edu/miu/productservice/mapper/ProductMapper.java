package edu.miu.productservice.mapper;

import edu.miu.productservice.dto.ProductDto;
import edu.miu.productservice.entity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    private final ModelMapper mapper;

    public ProductDto toDto(Product product) {
        return mapper.map(product, ProductDto.class);
    }

    public Set<ProductDto> toDtos(Set<Product> product) {
        return product.stream().map(this::toDto).collect(Collectors.toSet());
    }
}
