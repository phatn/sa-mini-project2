package edu.miu.paypalservice.controller;

import edu.miu.paypalservice.dto.PaymentRequestDTO;
import edu.miu.paypalservice.service.PaypalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/api/paypal")
@RequiredArgsConstructor
public class PaypalController {

    private final PaypalService paypalService;

    @PostMapping
    public void pay(@RequestBody PaymentRequestDTO body) {
        if (Objects.isNull(body.getPaymentMethod().getPaypalNumber())
                || Objects.isNull(body.getPaymentMethod().getPaypalToken())) {
            paypalService.orderStatus(body.getOrderNumber(), "failed", "Missing information on paypal transaction");
        } else {
            paypalService.orderStatus(body.getOrderNumber(), "paid", "");
            paypalService.shipToAddress(body);
        }
    }
}
