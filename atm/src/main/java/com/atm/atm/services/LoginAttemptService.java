package com.atm.atm.services;

import com.atm.atm.models.LoginAttempts;
import com.atm.atm.repositories.LoginAttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoginAttemptService {

    private static final int MAX_ATTEMPTS = 3;
    private static final int BLOCK_TIME_MINUTES = 15;

    private final LoginAttemptRepository loginAttemptRepository;

    @Autowired
    public LoginAttemptService(LoginAttemptRepository loginAttemptRepository) {
        this.loginAttemptRepository = loginAttemptRepository;
    }

    public void loginSucceeded(String username) {
        loginAttemptRepository.deleteByUsername(username);
    }

    public void loginFailed(String username, String ip) {
        LoginAttempts attempt = new LoginAttempts();
        attempt.setUsername(username);
        attempt.setIpAddress(ip);
        attempt.setAttemptTime(LocalDateTime.now());

        loginAttemptRepository.save(attempt);
    }

    public boolean isBlocked(String username) {
        LocalDateTime cutoff = LocalDateTime.now().minusMinutes(BLOCK_TIME_MINUTES);
        List<LoginAttempts> attempts = loginAttemptRepository.findAllByUsernameAndAttemptTimeAfter(username, cutoff);
        return attempts.size() >= MAX_ATTEMPTS;
    }
}
