package com.ghenriqf.login_auth_api.controller;


import com.ghenriqf.login_auth_api.domain.user.User;
import com.ghenriqf.login_auth_api.dto.request.LoginUserRequest;
import com.ghenriqf.login_auth_api.dto.request.RegisterUserRequest;
import com.ghenriqf.login_auth_api.dto.response.LoginUserResponse;
import com.ghenriqf.login_auth_api.dto.response.RegisterUserResponse;
import com.ghenriqf.login_auth_api.infra.security.TokenService;
import com.ghenriqf.login_auth_api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager; // responsável por fazer o gerenciamento da autenticação
    private final TokenService tokenService;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> login(@Valid @RequestBody LoginUserRequest loginUserRequest) {

        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(loginUserRequest.email(),loginUserRequest.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        User user = (User) authentication.getPrincipal();
        String token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginUserResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserResponse> register(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        User user = new User();
        user.setName(registerUserRequest.name());
        user.setName(registerUserRequest.name());
        user.setPassword(passwordEncoder.encode(registerUserRequest.password()));

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new RegisterUserResponse(user.getName(),user.getEmail())
        );
    }
}
