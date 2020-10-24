package io.github.guerra08.springwebfluxexample.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class SecurityContext implements ServerSecurityContextRepository {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, org.springframework.security.core.context.SecurityContext context) {
        return null;
    }

    @Override
    public Mono<org.springframework.security.core.context.SecurityContext> load(ServerWebExchange exchange) {
        String auth = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String token = null;
        if(auth != null && auth.startsWith("Bearer ")){
            token = auth.replace("Bearer " , "");
        }
        if(token != null){
            Authentication a = new UsernamePasswordAuthenticationToken(token, token);
            return this.authenticationManager.authenticate(a).map(SecurityContextImpl::new);
        }
        else{
            return Mono.empty();
        }
    }
}
