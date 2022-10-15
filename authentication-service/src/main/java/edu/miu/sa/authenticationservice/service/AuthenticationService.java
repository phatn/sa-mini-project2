package edu.miu.sa.authenticationservice.service;

import edu.miu.sa.authenticationservice.dto.LoginDto;
import edu.miu.sa.authenticationservice.dto.LoginResponse;

public interface AuthenticationService {

    LoginResponse authenticate(LoginDto loginDto);
}
