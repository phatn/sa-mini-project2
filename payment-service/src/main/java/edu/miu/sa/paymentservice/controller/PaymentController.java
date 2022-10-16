package edu.miu.sa.paymentservice.controller;

import edu.miu.sa.paymentservice.entity.Payment;
import edu.miu.sa.paymentservice.service.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
@Slf4j
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @PostMapping
    public void checkout(@RequestBody Payment payment) {
        paymentService.pay(payment);
    }
}
