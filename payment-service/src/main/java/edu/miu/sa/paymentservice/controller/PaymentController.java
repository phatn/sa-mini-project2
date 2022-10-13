package edu.miu.sa.paymentservice.controller;

import edu.miu.sa.paymentservice.entity.Payment;
import edu.miu.sa.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    public Payment getInfoPayment() {
        return paymentService.getInfoPayment();
    }
}
