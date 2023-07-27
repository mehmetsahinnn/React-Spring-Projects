package com.humanresources.hr.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = true)
    private Manager manager;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private double salary;

}
