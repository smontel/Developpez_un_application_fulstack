package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.DTO.auth.UserDTO;
import com.openclassrooms.mddapi.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDTO(User user){
        UserDTO dto= new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());

        return dto;
    }
}
