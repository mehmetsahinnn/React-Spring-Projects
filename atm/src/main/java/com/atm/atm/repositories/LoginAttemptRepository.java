package com.atm.atm.repositories;

import com.atm.atm.models.LoginAttempts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempts, Long> {
    void deleteByUsername(String username);
    List<LoginAttempts> findAllByUsernameAndAttemptTimeAfter(String username, LocalDateTime attemptTime);
}
