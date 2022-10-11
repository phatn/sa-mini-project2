package edu.miu.accountservice.controller;

import edu.miu.accountservice.dto.LoginDto;
import edu.miu.accountservice.dto.LoginResponse;
import edu.miu.accountservice.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login")
    public Mono<LoginResponse> login(@RequestBody LoginDto loginDto) {
        return Mono.fromSupplier(() -> loginService.login(loginDto));
    }
}
