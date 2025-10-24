package com.ghenriqf.login_auth_api.infra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
// OncePerRequestFilter garante que o filtro será executado uma vez por requisição HTTP
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public SecurityFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Authorization: Bearer <token>
        String authorizeHeader = request.getHeader("Authorization");

        // Verifica se o token existe e tem o prefixo correto
        if (Strings.isNotEmpty(authorizeHeader) && authorizeHeader.startsWith("Bearer ")) {
            String token = authorizeHeader.substring("Bearer ".length());
            Optional<JWTUserData> optUser = tokenService.validateToken(token);

            if (optUser.isPresent()) {
                JWTUserData userData = optUser.get();
                // usuário autenticado
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userData, null, null);

                // Coloca esse token no SecurityContextHolder, que é onde o Spring guarda o usuário logado durante a requisição.
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Continua as cadeias de filtros
            }
            filterChain.doFilter(request,response);
        } else {
            filterChain.doFilter(request,response);
        }
    }
}