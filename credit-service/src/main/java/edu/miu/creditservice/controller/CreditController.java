package edu.miu.creditservice.controller;

import edu.miu.creditservice.dto.PaymentRequestDTO;
import edu.miu.creditservice.service.CreditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("api/credit")
@RequiredArgsConstructor
public class CreditController {
    private final CreditService creditService;

    @PostMapping("pay")
    public void pay(@RequestBody PaymentRequestDTO body) {
        if (Objects.isNull(body.getPaymentMethod().getCardExpires())
                || Objects.isNull(body.getPaymentMethod().getCardNumber())
                || Objects.isNull(body.getPaymentMethod().getCardSecurityCode())) {
            creditService.orderStatus(body.getOrderNumber(), "failed", "Missing information on credit transaction");
        } else {
            creditService.orderStatus(body.getOrderNumber(), "paid", "");
            creditService.shipToAddress(body);
        }
    }
}
