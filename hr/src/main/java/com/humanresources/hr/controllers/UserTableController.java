package com.humanresources.hr.controllers;

import com.humanresources.hr.models.Employee;
import com.humanresources.hr.repositories.UserRepository;
import org.springframework.expression.ExpressionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:3000")
public class UserTableController {

    private final UserRepository userRepository;

    public UserTableController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Employee> getAllUsers() {
        return userRepository.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateUser(@PathVariable Integer id, @RequestBody Employee userDetails) {
        Employee user = userRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new ExpressionException("User not found with id :" + id));

        user.setName(userDetails.getName());
        user.setPassword(userDetails.getPassword());
        user.setSalary(userDetails.getSalary());

        return ResponseEntity.ok(userRepository.save(user));
    }
}