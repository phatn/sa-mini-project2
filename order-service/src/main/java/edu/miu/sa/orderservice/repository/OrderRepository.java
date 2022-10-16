package edu.miu.sa.orderservice.repository;

import edu.miu.sa.orderservice.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
}
