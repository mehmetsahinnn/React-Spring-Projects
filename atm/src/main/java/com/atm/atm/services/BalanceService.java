package com.atm.atm.services;

import com.atm.atm.models.User;
import com.atm.atm.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class BalanceService {

    private final UserRepository userRepository;

    public BalanceService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public double getBalanceForUser(String username) {
        User user = userRepository.findByUsername(username);
        return user.getBalance();
    }
}
