package edu.miu.sa.orderservice.service;

import edu.miu.sa.orderservice.dto.OrderDto;
import edu.miu.sa.orderservice.entity.Order;

import java.util.Set;

public interface OrderService {

    void save(OrderDto orderDto);

    Set<Order> getOrders();

    Order getById(int id);
}
