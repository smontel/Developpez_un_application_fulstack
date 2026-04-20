package com.openclassrooms.mddapi.DTO.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateDTO {
    private String name;
    private String email;
    private String password;
}