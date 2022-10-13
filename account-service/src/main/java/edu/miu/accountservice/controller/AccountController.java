package edu.miu.accountservice.controller;

import edu.miu.accountservice.dto.AccountDto;
import edu.miu.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
@Slf4j
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    public AccountDto findAccountById(@PathVariable int id) {
        return accountService.findById(id);
    }

    @GetMapping("/info/{email}")
    public AccountDto findAccountByEmail(@PathVariable String email) {
        return accountService.findByEmail(email);
    }
}
