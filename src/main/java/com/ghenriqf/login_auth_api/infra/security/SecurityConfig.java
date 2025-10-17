package com.ghenriqf.login_auth_api.infra.security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // diz ao Spring que essa classe contém beans de configuração, ou seja, objetos que o Spring cria e gerencia automaticamente.
@EnableWebSecurity // habilita o Spring Security, permitindo configurar autenticação e autorização.
public class SecurityConfig { // configuração de segurança do Spring Security

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http ) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configure(http))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll().requestMatchers(HttpMethod.POST, "/auth/login").permitAll().requestMatchers(HttpMethod.POST, "/auth/register").permitAll().anyRequest().authenticated()).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
