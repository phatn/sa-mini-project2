package edu.miu.sa.paymentservice.client;

import edu.miu.sa.paymentservice.entity.PaymentMethod;
import org.springframework.web.bind.annotation.PostMapping;

public interface AccountClient {

    @PostMapping("/account/method")
    PaymentMethod getPaymentMethod();
}
