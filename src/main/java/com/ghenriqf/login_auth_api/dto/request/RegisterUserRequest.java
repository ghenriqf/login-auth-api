package com.ghenriqf.login_auth_api.dto.request;

import jakarta.validation.constraints.NotEmpty;

public record RegisterUserRequest(
        @NotEmpty(message = "Nome de usuário obrigatório") String name,
        @NotEmpty(message = "Email é obrigatório") String email,
        @NotEmpty(message = "Senha é obrigatória") String password
) {
}
