package edu.miu.bankservice.service;

import edu.miu.bankservice.dto.PaymentRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BankService {
    public void shipToAddress(PaymentRequestDTO paymentRequest) {
        // ship to address
        //ship(paymentRequest.getOrderNumber(), paymentRequest.getAddress());
    }

    public void orderStatus(String orderNumber, String status, String reason) {
        Map<String, Object> body = new HashMap<>();
        if (Objects.nonNull(reason)) {
            body.put("reason", reason);
        }
        // update order status
        //updateStatus(orderNumber, status, body);
    }
}
