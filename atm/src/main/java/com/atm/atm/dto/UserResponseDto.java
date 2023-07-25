package com.atm.atm.dto;

import lombok.Data;

@Data
public class UserResponseDto {

    private Integer id;
    private String username;
    private Double balance;

}
