package edu.miu.creditservice.controller;

import edu.miu.creditservice.entity.PaymentDto;
import edu.miu.creditservice.service.CreditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/credit")
@RequiredArgsConstructor
public class CreditController {
    private final CreditService creditService;

    @PostMapping
    public void pay(@RequestBody PaymentDto payment) {
        creditService.shipToAddress(payment);
    }
}
