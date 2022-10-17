package edu.miu.paypalservice.controller;

import edu.miu.paypalservice.entity.Payment;
import edu.miu.paypalservice.service.PaypalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/paypal")
@RequiredArgsConstructor
public class PaypalController {

    private final PaypalService paypalService;

    @PostMapping
    public void pay(@RequestBody Payment payment) {
        paypalService.shipToAddress(payment);
    }
}
