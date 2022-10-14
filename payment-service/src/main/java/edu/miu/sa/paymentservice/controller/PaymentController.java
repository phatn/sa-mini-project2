package edu.miu.sa.paymentservice.controller;

import edu.miu.sa.paymentservice.entity.PaymentMethod;
import edu.miu.sa.paymentservice.entity.PaymentRequest;
import edu.miu.sa.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/checkout")
    public void checkout(@RequestBody PaymentRequest body) {
        if(Objects.isNull(body.getPaymentMethod())) {
            body.setPaymentMethod(paymentService.getInfoPayment());
        }
        Optional.ofNullable(body.getPaymentMethod()).ifPresentOrElse(method -> paymentService.decidePayment(body),
                () -> paymentService.failedPayment(body.getOrderNumber(), "Payment method required!"));
    }
}
