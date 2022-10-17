package edu.miu.sa.paymentservice.service;

import edu.miu.sa.paymentservice.entity.Order;

public interface PaymentService {

    void pay(Order order);
}
