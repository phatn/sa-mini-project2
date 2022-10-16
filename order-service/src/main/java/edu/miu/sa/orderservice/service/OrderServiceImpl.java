package edu.miu.sa.orderservice.service;

import com.google.common.collect.Sets;
import edu.miu.sa.orderservice.entity.Order;
import edu.miu.sa.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.oauth2.jwt.Jwt;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final RestTemplate restTemplate;

    private final OrderRepository orderRepository;

    @Override
    public void save(Order order) {
        Jwt jwt = (Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = String.valueOf(jwt.getClaims().get("email"));
        order.setAccountEmail(email);

        orderRepository.save(order);
    }

    @Override
    public Set<Order> getOrders() {
        return Sets.newHashSet(orderRepository.findAll());
    }

    @Override
    public Order getById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot find order: " + id));
    }
}
