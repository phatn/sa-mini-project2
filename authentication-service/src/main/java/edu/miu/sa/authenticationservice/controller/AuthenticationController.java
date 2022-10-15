package edu.miu.sa.authenticationservice.controller;

import edu.miu.sa.authenticationservice.dto.LoginDto;
import edu.miu.sa.authenticationservice.dto.LoginResponse;
import edu.miu.sa.authenticationservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/authenticate")
    public LoginResponse login(@RequestBody LoginDto loginDto) {
        return authenticationService.authenticate(loginDto);
    }

}
