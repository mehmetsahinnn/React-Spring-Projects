package com.jr.jobresearch.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "admin")
public class Admin {
    @Id
    private Integer id;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;
}

