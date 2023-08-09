package com.jr.jobresearch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterDTO {

    @NotBlank(message = "Username is mandatory")
    private String username;

    @Size(min = 4, message = "Password should be at least 6 characters long")
    private String password;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number should be 10 digits")
    private String phoneNumber;

    private Boolean militaryStatus;

    @Size(max = 500, message = "Job experience description should not exceed 500 characters")
    private String jobExperience;
}
