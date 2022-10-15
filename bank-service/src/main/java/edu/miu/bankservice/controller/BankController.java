package edu.miu.bankservice.controller;

import edu.miu.bankservice.dto.PaymentRequestDTO;
import edu.miu.bankservice.service.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("api/bank")
@RequiredArgsConstructor
public class BankController {
    private final BankService bankService;

    @PostMapping("pay")
    public void pay(@RequestBody PaymentRequestDTO body) {
        if (Objects.isNull(body.getPaymentMethod().getBankAccount())
                || Objects.isNull(body.getPaymentMethod().getBankName())
                || Objects.isNull(body.getPaymentMethod().getBankRouting())) {
            bankService.orderStatus(body.getOrderNumber(), "failed", "Missing information on bank transaction");
        } else {
            bankService.orderStatus(body.getOrderNumber(), "paid", "");
            bankService.shipToAddress(body);
        }
    }
}
