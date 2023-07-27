package com.humanresources.hr.services;

import com.humanresources.hr.models.Employee;
import com.humanresources.hr.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Employee findByUsername(String username){
        return userRepository.findByName(username);
    }
}
