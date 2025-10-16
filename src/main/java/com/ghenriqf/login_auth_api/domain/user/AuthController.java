package com.ghenriqf.login_auth_api.domain.user;


import com.ghenriqf.login_auth_api.dto.request.LoginUserRequest;
import com.ghenriqf.login_auth_api.dto.request.RegisterUserRequest;
import com.ghenriqf.login_auth_api.dto.response.LoginUserResponse;
import com.ghenriqf.login_auth_api.dto.response.RegisterUserResponse;
import com.ghenriqf.login_auth_api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<LoginUserResponse> login(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        return null;
    }

    @PostMapping
    public ResponseEntity<RegisterUserResponse> register(
            @Valid @RequestBody RegisterUserRequest registerUserRequest
    ) {
        User user = new User();
        user.setName(registerUserRequest.name());
        user.setName(registerUserRequest.name());
        user.setPassword(registerUserRequest.password());

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new RegisterUserResponse(user.getName(),user.getEmail())
        );
    }
}
