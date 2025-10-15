package com.ghenriqf.login_auth_api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ghenriqf.login_auth_api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer("login-auth-api") // Define quem emitiu o token. Isso ajuda a validar depois se o token veio do seu servidor
                    .withSubject(user.getEmail()) // Define o assunto do token, ou seja, o usuário a quem ele pertence. Aqui está usando o e-mail do usuário
                    .withExpiresAt(this.generateExpirationDate()) // Define quando o token expira
                    .sign(algorithm); // Assina o token com o algoritmo HMAC256 e a chave secreta
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro na autentição");
        }
    }

    public String validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm) // Cria o algoritmo com a mesma secret usada na geração
                    .withIssuer("login-auth-api")// Exige que o campo iss (emissor) do token seja "login-auth-api". Isso garante que o token foi criado pela sua aplicação e não por outra.
                    .build() // Monta o verificador, exigindo que o emissor seja o mesmo
                    .verify(token) // Verifica assinatura, expiração e integridade
                    .getSubject();
        } catch (JWTVerificationException exception) {
            return null; // Retorna null se o token for inválido ou expirado
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
