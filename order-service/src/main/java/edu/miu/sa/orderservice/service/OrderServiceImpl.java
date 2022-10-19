package edu.miu.sa.orderservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import edu.miu.sa.orderservice.contant.Constant;
import edu.miu.sa.orderservice.dto.*;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Value("${account-service-secret-key}")
    private String accountSecretKey;

    @Value("${account-service-url}")
    private String accountServiceUrl;

    @Override
    public void save(OrderDto orderDto) {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = String.valueOf(jwt.getClaims().get("email"));

        HttpHeaders headersProduct = new HttpHeaders();
        headersProduct.set(Constant.X_PRODUCT_SERVICE_KEY, productSecretKey);
        headersProduct.set("Content-Type", "application/json");
        String ids = orderDto.getOrderItems().stream().map(o -> String.valueOf(o.getProductId())).collect(Collectors.joining(","));
        String url = productServiceUrl + "?ids=" + ids;
        log.error("url of product service: {}", url);
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
            log.error("Error the get product details!", ex);
            throw new RuntimeException("Error the get product details!" + ex.getMessage());
        }

        // Update product quantity
        List<Product> products = orderDto.getOrderItems().stream()
                .map(o -> Product.builder()
                        .id(o.getProductId())
                        .quantity(o.getQuantity())
                        .build())
                .collect(Collectors.toList());
        try {
            HttpEntity<List<Product>> httpEntityProductQuantity = new HttpEntity<>(products, headersProduct);
            restTemplate.postForEntity(productServiceUrl, httpEntityProductQuantity, Void.class);
            log.info("Updated product quantities done!");
        } catch(Exception e) {
            log.error("Error to update product quantities: ", e);
            throw new RuntimeException("Error to update product quantities: " + e.getMessage());
        }

        // Get info from account
        HttpHeaders headersAccount = new HttpHeaders();
        headersAccount.set(Constant.X_ACCOUNT_SERVICE_KEY, accountSecretKey);
        headersAccount.set("Content-Type", "application/json");
        String urlAccount = accountServiceUrl + "?email=" + email;
        Address addressShipping = null;
        Account accountShipping = null;
        Payment paymentInfo = null;
        try {
            HttpEntity<Account> httpAccount = new HttpEntity<>(headersAccount);
            accountShipping = restTemplate.exchange(urlAccount, HttpMethod.GET, httpAccount, new ParameterizedTypeReference<Account>(){}).getBody();
            log.info("PAYMENT account: " + accountShipping);

            if (orderDto.getStreet() == null && orderDto.getCity() == null && orderDto.getZipCode() == null) {
                for (Address address : accountShipping.getAddress()) {
                    if (address.getId() == accountShipping.getPreferredAddress()) {
                        addressShipping = address;
                    }
                }
                log.info("PAYMENT address: " + addressShipping);
                orderDto.setCity(addressShipping.getCity());
                orderDto.setZipCode(addressShipping.getZipCode());
                orderDto.setStreet(addressShipping.getStreet());
            }

            if (orderDto.getPaymentType() == null) {
                for (Payment payment : accountShipping.getPayment()) {
                    if (payment.getId() == accountShipping.getPreferredPayment()) {
                        paymentInfo = payment;
                    }
                }
                log.info("PAYMENT payment info: " + paymentInfo);
                orderDto.setPaymentType(paymentInfo.getType());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error to send request to payment service!" + e.getMessage());
        }

        // Send request to shipping
        HttpHeaders headersShipping = new HttpHeaders();
        headersShipping.set(Constant.X_PAYMENT_SERVICE_KEY, paymentSecretKey);
        headersShipping.set("Content-Type", "application/json");
        PaymentDto paymentDto = PaymentDto.builder()
                .type(orderDto.getPaymentType())
                .accountEmail(email)
                .paymentMap(orderDto.getPayment())
                .city(orderDto.getCity())
                .zipCode(orderDto.getZipCode())
                .street(orderDto.getStreet())
                .firstName(accountShipping.getFirstName())
                .lastName(accountShipping.getLastName())
                .build();

        try {
            HttpEntity<PaymentDto> requestEntity = new HttpEntity<>(paymentDto, headersShipping);
            String response = restTemplate.postForEntity(paymentServiceUrl, requestEntity, String.class).getBody();
            log.info("Response from Shipping Service: " + response);
        } catch (Exception e) {
            log.error("Error to send request to paymentDto service!", e);
            throw new RuntimeException("Error to send request to paymentDto service!" + e.getMessage());
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
