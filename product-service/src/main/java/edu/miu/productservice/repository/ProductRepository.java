package edu.miu.productservice.repository;

import edu.miu.productservice.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query("SELECT p from Product p WHERE p.id IN :ids")
    Set<Product> findProductByIds(List<Integer> ids);
}
