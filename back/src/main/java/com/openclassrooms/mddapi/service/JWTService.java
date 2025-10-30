package com.openclassrooms.mddapi.service;


import java.time.Instant;
import java.time.temporal.ChronoUnit;

import com.openclassrooms.mddapi.DTO.auth.LoginDTO;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;


@Service
public class JWTService {


    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;

    public JWTService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {

        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public String generateToken(LoginDTO loginDTO) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(loginDTO.getEmail())
                .build();
        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
    }

    public String extractEmail(String token) {
        try {
            Jwt jwt = jwtDecoder.decode(token);
            return jwt.getSubject(); // ou jwt.getClaim("email") si nécessaire
        } catch (JwtException e) {
            throw new RuntimeException("Token invalide", e);
        }
    }
}