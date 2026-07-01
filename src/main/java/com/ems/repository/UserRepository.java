package com.ems.repository;

import com.ems.entity.Role;
import com.ems.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Authentication
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    // Dashboard Statistics
    long countByRole(Role role);

    long countByActiveTrue();

}