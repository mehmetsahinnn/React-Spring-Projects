package com.jr.jobresearch.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "logs")
public class Logs {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "timestamp")
    private Date timestamp;

    @Column(nullable = false, name = "userId")
    private Integer userId;

    @Column(nullable = false, name = "actionDescription")
    private String actionDescription;
}
