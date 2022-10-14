package edu.miu.sa.paymentservice.client;

import edu.miu.sa.paymentservice.entity.PaymentRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface BankClient {
    @PostMapping("/pay")
    void checkout(@RequestBody PaymentRequest body);
}
