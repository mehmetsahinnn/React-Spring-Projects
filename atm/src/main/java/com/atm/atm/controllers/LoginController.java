package com.atm.atm.controllers;

import com.atm.atm.dto.UserDto;
import com.atm.atm.dto.UserResponseDto;
import com.atm.atm.models.User;
import com.atm.atm.services.LoginAttemptService;
import com.atm.atm.services.UserService;
import com.atm.atm.util.SafePasswordPass;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    private final UserService userService;
    private final LoginAttemptService loginAttemptService;
    private final SafePasswordPass passwordEncoder;

    @Autowired
    public LoginController(UserService userService, LoginAttemptService loginAttemptService, SafePasswordPass passwordEncoder) {
        this.userService = userService;
        this.loginAttemptService = loginAttemptService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody UserDto userDto, HttpServletRequest request) {
        try {
            String ipAddress = request.getRemoteAddr();
            if (loginAttemptService.isBlocked(ipAddress)) {
                return new ResponseEntity<>(Collections.singletonMap("message", "Your IP has been blocked due to too many failed login attempts"), HttpStatus.UNAUTHORIZED);
            }

            User user = userService.findByUsername(userDto.getUsername());

            if (user == null) {
                loginAttemptService.loginFailed(userDto.getUsername(), ipAddress);
                return new ResponseEntity<>(Collections.singletonMap("message", "User not found"), HttpStatus.NOT_FOUND);
            } else if (!passwordEncoder.passwordEncoder().matches(userDto.getPassword(), user.getPassword())) {
                loginAttemptService.loginFailed(userDto.getUsername(), ipAddress);
                return new ResponseEntity<>(Collections.singletonMap("message", "Invalid password"), HttpStatus.UNAUTHORIZED);
            } else {
                loginAttemptService.loginSucceeded(ipAddress);

                UserResponseDto userResponseDto = new UserResponseDto();
                userResponseDto.setId(user.getId());
                userResponseDto.setUsername(user.getUsername());
                userResponseDto.setBalance(user.getBalance());

                return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("message", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
