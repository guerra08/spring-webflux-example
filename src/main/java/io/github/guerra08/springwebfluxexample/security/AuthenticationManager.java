package io.github.guerra08.springwebfluxexample.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtProvider jwtProvider;

    @Autowired
    public AuthenticationManager(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        try {
            Jws<Claims> claimsJws = jwtProvider.validateJwt(authentication.getCredentials().toString());
            System.out.println(claimsJws.getBody());
            return Mono.just(new UsernamePasswordAuthenticationToken(
                    claimsJws.getBody().getSubject(),
                    authentication.getCredentials().toString(),
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))));
        }catch (Exception e){
            return Mono.empty();
        }
    }
}
