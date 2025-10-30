package com.openclassrooms.mddapi.DTO.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {
    private String email;
    private String password;
}


