package com.ghenriqf.login_auth_api.domain.user;

public enum UserRole {
    ROLE_ADMIN("admin"),
    ROLE_USER("user");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole () {
        return role;
    }
}
