package edu.miu.productservice.repository;

import edu.miu.productservice.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
