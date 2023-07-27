package com.humanresources.hr.dto;

import com.humanresources.hr.models.Manager;
import lombok.Data;

@Data
public class EmployeeDto {
    private String name;
    private String email;
    private String password;
    private Double salary;
}
