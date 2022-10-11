package edu.miu.accountservice.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public final class GrantedAuthoritiesExtractor implements Converter<Jwt, Collection<GrantedAuthority>> {

    public Collection<GrantedAuthority> convert(Jwt jwt) {
        Collection<?> authorities = (Collection<?>)
                jwt.getClaims().getOrDefault("roles", Collections.emptyList());
        String email = (String)jwt.getClaims().getOrDefault("email", "");

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, null);
        ReactiveSecurityContextHolder.getContext()
                .doOnSuccess(context -> context.setAuthentication(authentication));

        return authorities.stream()
                .map(Object::toString)
                .map(r -> "ROLE_" + r)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}
