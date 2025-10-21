package com.ghenriqf.login_auth_api.infra.security;

import lombok.Builder;
import java.util.UUID;

@Builder
public record JWTUserData(UUID userId, String email) {
}
