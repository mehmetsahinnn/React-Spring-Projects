package com.jr.jobresearch.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "jobTitle")
    private String jobTitle;

    @Column(name = "jobDescription")
    private String jobDescription;

    @Column(name = "requiredExperience")
    private Integer requiredExperience;

    @Column(name = "militaryRequirement")
    private Boolean militaryRequirement;
}
