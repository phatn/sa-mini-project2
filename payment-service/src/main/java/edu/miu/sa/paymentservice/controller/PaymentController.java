package edu.miu.sa.paymentservice.controller;

import edu.miu.sa.paymentservice.dto.OrderResponse;
import edu.miu.sa.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/checkout")
    public void checkout(@RequestBody OrderResponse body) {
        log.info("Payment method is {}", body);
        Optional.ofNullable(body.getPaymentMethod()).ifPresentOrElse(method -> paymentService.decidePayment(body),
                () -> paymentService.failedPayment(body.getOrderNumber(), "Payment method required"));
    }
}
