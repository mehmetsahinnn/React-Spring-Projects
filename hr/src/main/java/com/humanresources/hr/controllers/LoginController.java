package com.humanresources.hr.controllers;


import com.humanresources.hr.dto.EmployeeDto;
import com.humanresources.hr.dto.EmployeeResponseDto;
import com.humanresources.hr.models.Employee;
import com.humanresources.hr.services.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody EmployeeDto employeeDto) {

        try {

            Employee user = loginService.findByUsername(employeeDto.getName());

            EmployeeResponseDto employee = new EmployeeResponseDto();
            employee.setId(user.getId());
            employee.setUsername(employeeDto.getName());

            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
