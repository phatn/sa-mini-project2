package edu.miu.sa.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import edu.miu.sa.orderservice.dto.Product;
import edu.miu.sa.orderservice.entity.Order;
import edu.miu.sa.orderservice.entity.OrderItem;
import edu.miu.sa.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final RestTemplate restTemplate;

    private final ObjectMapper mapper;

    private final OrderRepository orderRepository;

    @Value("${product-service-secret-key}")
    private String secretKey;

    @Value("${product-service-url}")
    private String productServiceUrl;

    @Override
    public void save(Order order) {
        Jwt jwt = (Jwt)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = String.valueOf(jwt.getClaims().get("email"));
        order.setAccountEmail(email);
        HttpHeaders headers = createHeaders();
        String ids = order.getOrderItems().stream().map(o -> String.valueOf(o.getProductId())).collect(Collectors.joining(","));
        String url = productServiceUrl + "?ids="+ ids;
        HttpEntity<Product> httpEntity = new HttpEntity<>(headers);
        Set<Product> products = restTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Set<Product>>() {}).getBody();
        double total = 0;
        for(OrderItem orderItem : order.getOrderItems()) {
            for(Product product : products) {
                if(orderItem.getProductId() == product.getId()) {
                    total += orderItem.getQuantity() * product.getPrice();
                }
            }
        }
        order.setTotal(total);
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

    private HttpHeaders createHeaders(){
        return new HttpHeaders() {{
            set( "X-PRODUCT-SERVICE-KEY", secretKey );
            set("Content-Type", "application/json");
        }};
    }
}
