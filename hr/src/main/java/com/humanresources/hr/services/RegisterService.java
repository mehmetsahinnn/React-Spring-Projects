package com.humanresources.hr.services;

import com.humanresources.hr.dto.EmployeeDto;
import com.humanresources.hr.models.Employee;
import com.humanresources.hr.repositories.UserRepository;
import com.humanresources.hr.util.SafePasswordPass;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(EmployeeDto employeeDto) throws IllegalArgumentException {
        if (employeeDto.getName() == null || employeeDto.getEmail() == null || employeeDto.getPassword() == null) {
            throw new IllegalArgumentException("Name, Email, and Password cannot be null");
        }
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setEmail(employeeDto.getEmail());
        employee.setPassword(passwordEncoder.encode(employeeDto.getPassword()));
        employee.setSalary(employeeDto.getSalary());
        userRepository.save(employee);
    }
}

