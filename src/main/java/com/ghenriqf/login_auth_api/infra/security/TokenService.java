package com.ghenriqf.login_auth_api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ghenriqf.login_auth_api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim("userId", user.getId().toString()) // informações personalizadas chamadas claims
                .withSubject(user.getEmail()) // Define o subject (sub) do token campo padrão do JWT.
                .withIssuedAt(Instant.now()) // Define a data de emissão do token
                .withExpiresAt(generateExpirationDate()) // Define a data de expiração do token
                .sign(algorithm); // token é assinado criptograficamente com o algoritmo definido.
    }
    /*
    JWT = HEADER . PAYLOAD . SIGNATURE
    Exemplo do payload do JWT gerado
    {
      "userId": "f6d1bcb8-9e8d-4e0a-9efb-2f4b9c2b8a77",
      "sub": "gabriel@email.com",
      "iat": 1729612025,
      "exp": 1729615625
    }
    */

    // Gera a data de expiração do token: 2 horas (7200 segundos) a partir do momento atual
    private Instant generateExpirationDate() {
        return Instant.now().plusSeconds(7200);
    }

    public Optional<JWTUserData> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decode = JWT.require(algorithm)// cria um verificador que exige que o token seja assinado com o algoritmo e a chave correta.
                    .build() // constrói o verificador real (JWTVerifier) a partir do builder.
                    .verify(token); // Valida o token: assinatura, expiração e integridade; retorna DecodedJWT se válido ou lança JWTVerificationException se inválido.

            return Optional.of(JWTUserData.builder()
                    .userId(UUID.fromString(decode.getClaim("userId").asString()))
                    .email(decode.getSubject())
                    .build());
        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }
}
