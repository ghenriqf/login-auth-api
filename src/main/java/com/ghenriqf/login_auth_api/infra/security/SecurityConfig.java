package com.ghenriqf.login_auth_api.infra.security;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // diz ao Spring que essa classe contém beans de configuração, ou seja, objetos que o Spring cria e gerencia automaticamente.
@EnableWebSecurity // habilita o Spring Security, permitindo configurar autenticação e autorização.
public class SecurityConfig { // configuração de segurança do Spring Security

    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    // cadeia de filtros que o Spring Security vai aplicar em cada requisição HTTP.
    public SecurityFilterChain securityFilterChain (HttpSecurity http ) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Desativa o CSRF (Cross-Site Request Forgery).
                .cors(cors -> cors.configure(http)) // Configura o CORS (Cross-Origin Resource Sharing), permitindo que clientes em outros domínios possam acessar a API.
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Define que a aplicação é stateless, ou seja, não usa sessão.
                .authorizeHttpRequests(authorize -> authorize // Define quem pode acessar cada endpoint:
                        .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build(); // gera o SecurityFilterChain, que é a cadeia de filtros de segurança usada em cada requisição
    }

    // Cria o AuthenticationManager, que é o responsável por validar usuário e senha no login.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Cria um codificador de senha (BCrypt) para armazenar e comparar senhas criptografadas.
    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
