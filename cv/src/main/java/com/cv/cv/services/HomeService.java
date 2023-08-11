package com.cv.cv.services;

import com.cv.cv.models.User;
import com.cv.cv.repositories.HomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HomeService {
    private final HomeRepository homeRepository;

    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public List<User> getAllUsers() {
        return homeRepository.findAll();
    }

    public User getUserById(Long id) {
        return homeRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        return homeRepository.save(user);
    }

    public User updateUser(Long id, User user) {
        Optional<User> existingUser = homeRepository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setWorkExperience(user.getWorkExperience());
            updatedUser.setPhoneNumber(user.getPhoneNumber());
            updatedUser.setCompletedMilitaryService(user.isCompletedMilitaryService());

            return homeRepository.save(updatedUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public void deleteUser(Long id) {
        homeRepository.deleteById(id);
    }
}
