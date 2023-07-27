package com.humanresources.hr.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "admins")
public class Manager {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonBackReference
    @OneToMany(mappedBy = "manager")
    private List<Employee> users;

    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Double salary;
}
