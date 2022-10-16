package edu.miu.accountservice.controller;

import edu.miu.accountservice.dto.AccountDto;
import edu.miu.accountservice.exception.AuthenticationException;
import edu.miu.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    @Value("${account-service-secret-key}")
    private String secretKey;

    private final HttpServletRequest request;
    private final AccountService accountService;

    @GetMapping("/api/{id}")
    public AccountDto findAccountById(@PathVariable int id) {
        return accountService.findById(id);
    }

    @GetMapping("/api/email/{email}")
    public AccountDto findAccountByEmail(@PathVariable String email) {
        return accountService.findByEmail(email);
    }

    @GetMapping("/service/account")
    public AccountDto findAccountServiceByEmail(@RequestParam String email) {

        if(!secretKey.equals(request.getHeader("X-ACCOUNT-SERVICE-KEY"))) {
            throw new AuthenticationException("Authentication failed");
        }
        return accountService.findByEmail(email);
    }
}
