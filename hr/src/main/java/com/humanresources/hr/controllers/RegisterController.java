package com.humanresources.hr.controllers;

import com.humanresources.hr.dto.EmployeeDto;
import com.humanresources.hr.services.RegisterService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
@CrossOrigin("http://localhost:3000")
public class RegisterController {

    private final RegisterService registerService;


    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody EmployeeDto employeeDto) {
        try {
            registerService.register(employeeDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
