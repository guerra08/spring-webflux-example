package io.github.guerra08.springwebfluxexample.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;

@Service
public class JwtProvider {

    private final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

    public String createJwt(String user){
        return Jwts.builder()
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .setSubject(user)
                .setIssuer("identity")
                .setExpiration(Date.from(Instant.now().plus(Duration.ofMinutes(10))))
                .setIssuedAt(Date.from(Instant.now()))
                .compact();

    }

    public Jws<Claims> validateJwt(String jwt) {
        return Jwts.parserBuilder().setSigningKey(keyPair.getPublic()).build().parseClaimsJws(jwt);
    }


}
