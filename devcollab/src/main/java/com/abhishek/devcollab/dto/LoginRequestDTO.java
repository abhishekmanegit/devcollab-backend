package com.abhishek.devcollab.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
    private String name;
}