package com.jr.jobresearch.services;

import com.jr.jobresearch.models.User;
import com.jr.jobresearch.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername(String name){
        return userRepository.findByUsername(name);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }


}
