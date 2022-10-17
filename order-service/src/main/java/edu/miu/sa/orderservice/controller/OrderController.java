package edu.miu.sa.orderservice.controller;

import edu.miu.sa.orderservice.dto.OrderDto;
import edu.miu.sa.orderservice.entity.Order;
import edu.miu.sa.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public Set<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable int id) {
        return orderService.getById(id);
    }

    @PostMapping
    public void save(@RequestBody OrderDto orderDto) {
        orderService.save(orderDto);
    }
}
