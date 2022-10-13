package edu.miu.productservice.repository;

import edu.miu.productservice.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
