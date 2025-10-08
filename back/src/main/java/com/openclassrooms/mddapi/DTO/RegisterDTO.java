package com.openclassrooms.mddapi.DTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegisterDTO {
    private String email;
    private String password;
    private String name;
}
