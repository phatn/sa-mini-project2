package edu.miu.bankservice.controller;

import edu.miu.bankservice.entity.Payment;
import edu.miu.bankservice.service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/bank")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @PostMapping
    public void pay(@RequestBody Payment payment) {
        bankService.shipToAddress(payment);
    }
}
