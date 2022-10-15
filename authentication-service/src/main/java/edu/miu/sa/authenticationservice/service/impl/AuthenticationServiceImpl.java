package edu.miu.sa.authenticationservice.service.impl;

import edu.miu.sa.authenticationservice.dto.LoginDto;
import edu.miu.sa.authenticationservice.dto.LoginResponse;
import edu.miu.sa.authenticationservice.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final RestTemplate restTemplate;

    @Value("${keycloak.auth-url}")
    private String authUrl;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.secret}")
    private String secret;

    @Override
    public LoginResponse authenticate(LoginDto loginDto) {
        HttpHeaders headers = createHeaders(clientId, secret);


        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("grant_type", "password");
        bodyMap.add("username", loginDto.getUsername());
        bodyMap.add("password", loginDto.getPassword());

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyMap, headers);
        return restTemplate.postForEntity(authUrl, requestEntity, LoginResponse.class).getBody();
    }

    private HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.getEncoder().encode(
                    auth.getBytes(StandardCharsets.US_ASCII) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
            set("Content-Type", "application/x-www-form-urlencoded");
        }};
    }

}
