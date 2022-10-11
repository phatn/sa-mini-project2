package edu.miu.accountservice.service;

import edu.miu.accountservice.dto.LoginDto;
import edu.miu.accountservice.dto.LoginResponse;
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
public class LoginService {
    private final RestTemplate restTemplate;

    public LoginService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${keycloak.auth-url}")
    private String authUrl;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.secret}")
    private String secret;

    public LoginResponse login(LoginDto loginDto) {
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
