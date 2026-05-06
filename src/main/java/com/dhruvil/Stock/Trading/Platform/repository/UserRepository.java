package com.dhruvil.Stock.Trading.Platform.repository;

import com.dhruvil.Stock.Trading.Platform.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}