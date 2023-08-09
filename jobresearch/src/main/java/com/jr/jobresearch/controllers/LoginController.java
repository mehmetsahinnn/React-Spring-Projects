package com.jr.jobresearch.controllers;


import com.jr.jobresearch.dto.LoginDTO;
import com.jr.jobresearch.models.Admin;
import com.jr.jobresearch.models.User;
import com.jr.jobresearch.services.AdminService;
import com.jr.jobresearch.services.UserService;
import com.jr.jobresearch.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class LoginController {

    private final UserService userService;
    private final AdminService adminService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public LoginController(UserService userService, AdminService adminService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.adminService = adminService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginRequest) {

        System.out.println("Received username: " + loginRequest.getUsername());
        System.out.println("Received password: " + loginRequest.getPassword());

        User user = userService.findByUsername(loginRequest.getUsername());
        Admin admin = adminService.findAdminByUsername(loginRequest.getUsername());

        Map<String, Object> response = new HashMap<>();
        String role;

        if (admin != null && bCryptPasswordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {

            response.put("token", JwtUtil.generateToken(admin.getUsername()));
            role = "admin";
        } else if (user != null && bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

            response.put("token", JwtUtil.generateToken(user.getUsername()));
            role = "user";
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", "Invalid credentials");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        response.put("role", role);
        return ResponseEntity.ok(response);
    }
}
