package io.github.guerra08.springwebfluxexample.controller;

import io.github.guerra08.springwebfluxexample.security.JwtProvider;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Getter
@Setter
class UserCredentials{
    private String username;
    private String password;
}

@RestController
public class AuthController {

    private final ReactiveUserDetailsService reactiveUserDetailsService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(ReactiveUserDetailsService reactiveUserDetailsService, JwtProvider jwtProvider, PasswordEncoder passwordEncoder) {
        this.reactiveUserDetailsService = reactiveUserDetailsService;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping("/login")
    public Mono<ResponseEntity<Object>> login(@RequestBody UserCredentials user){
        return reactiveUserDetailsService.findByUsername(user.getUsername())
                .map(e -> {
                    if(passwordEncoder.matches(user.getPassword(), e.getPassword())){
                        String jwt = jwtProvider.createJwt(e.getUsername());
                        ResponseCookie cookie = ResponseCookie.fromClientResponse("X-Auth", jwt)
                                .maxAge(600)
                                .httpOnly(true)
                                .path("/")
                                .secure(false)
                                .build();
                        return ResponseEntity
                                .noContent()
                                .header("Set-Cookie", cookie.toString())
                                .build();
                    }
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()));
    }

}
