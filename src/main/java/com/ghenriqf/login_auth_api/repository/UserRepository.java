package com.ghenriqf.login_auth_api.repository;

import com.ghenriqf.login_auth_api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<UserDetails> findByEmail(String username);
}
