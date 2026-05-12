package com.daoscrolls.core.repository;

import com.daoscrolls.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<String> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
