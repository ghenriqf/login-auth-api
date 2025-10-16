package com.ghenriqf.login_auth_api.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginUserRequest(
        @NotEmpty(message = "Email é obrigatório!") String email,
        @NotEmpty(message = "Senha é obrigatória!") String password) {
}
