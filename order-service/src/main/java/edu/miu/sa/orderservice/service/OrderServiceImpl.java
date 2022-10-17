package edu.miu.sa.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import edu.miu.sa.orderservice.contant.Constant;
import edu.miu.sa.orderservice.dto.OrderDto;
import edu.miu.sa.orderservice.dto.Payment;
import edu.miu.sa.orderservice.dto.Product;
import edu.miu.sa.orderservice.entity.Order;
import edu.miu.sa.orderservice.entity.OrderItem;
import edu.miu.sa.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final RestTemplate restTemplate;

    private final ObjectMapper mapper;

    private final OrderRepository orderRepository;

    @Value("${product-service-secret-key}")
    private String productSecretKey;

    @Value("${payment-service-secret-key}")
    private String paymentSecretKey;

    @Value("${product-service-url}")
    private String productServiceUrl;

    @Value("${payment-service-url}")
    private String paymentServiceUrl;

    @Override
    public void save(OrderDto orderDto) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = String.valueOf(jwt.getClaims().get("email"));

        HttpHeaders headersProduct = new HttpHeaders();
        headersProduct.set(Constant.X_PRODUCT_SERVICE_KEY, productSecretKey);
        headersProduct.set("Content-Type", "application/json");
        String ids = orderDto.getOrderItems().stream().map(o -> String.valueOf(o.getProductId())).collect(Collectors.joining(","));
        String url = productServiceUrl + "?ids=" + ids;
        HttpEntity<Product> httpEntity = new HttpEntity<>(headersProduct);

        try {
            Set<Product> products = restTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<Set<Product>>() {
            }).getBody();

            double total = 0;
            for (OrderItem orderItem : orderDto.getOrderItems()) {
                for (Product product : products) {
                    if (orderItem.getProductId() == product.getId()) {
                        total += orderItem.getQuantity() * product.getPrice();
                    }
                }
            }
            Order order = Order.builder()
                    .accountEmail(email)
                    .paymentType(orderDto.getPaymentType())
                    .total(total)
                    .build();
            order.setOrderItems(orderDto.getOrderItems());
            orderRepository.save(order);
        } catch (Exception ex) {
            throw new RuntimeException("Error the get product details!" + ex.getMessage());
        }

        // Send request to shipping
        HttpHeaders headersShipping = new HttpHeaders();
        headersShipping.set(Constant.X_PAYMENT_SERVICE_KEY, paymentSecretKey);
        headersShipping.set("Content-Type", "application/json");
        Payment payment = Payment.builder()
                .type(orderDto.getPaymentType())
                .accountEmail(email)
                .paymentMap(orderDto.getPayment())
                .build();

        try {
            HttpEntity<Payment> requestEntity = new HttpEntity<>(payment, headersShipping);
            String response = restTemplate.postForEntity(paymentServiceUrl, requestEntity, String.class).getBody();
            log.info("Response from Shipping Service: " + response);
        } catch (Exception e) {
            throw new RuntimeException("Error to send request to payment service!" + e.getMessage());
        }
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
