package com.cv.cv.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="first_name", nullable = false)
    private String name;
    @Column(name="last_name", nullable = false)
    private String lastName;
    @Column(name="phone_number", nullable = false)
    private String phoneNumber;
    @Column(name="work_experience", nullable = false)
    private String workExperience;
    @Column(name="completed_military_service", nullable = false)
    private boolean completedMilitaryService;

}
