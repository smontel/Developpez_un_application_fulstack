package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.DTO.ThemeDTO;
import com.openclassrooms.mddapi.DTO.auth.UserDTO;
import com.openclassrooms.mddapi.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDTO toDTO(User user){
        UserDTO dto= new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        if (user.getSubscribedThemes() != null && !user.getSubscribedThemes().isEmpty()) {
            List<ThemeDTO> themeDtos = user.getSubscribedThemes()
                    .stream()
                    .map(theme -> {
                        ThemeDTO iterationDTO = new ThemeDTO();
                        iterationDTO.setName(theme.getName());
                        iterationDTO.setId(theme.getId());
                        return iterationDTO;
                    })
                    .collect(Collectors.toList());
            dto.setSubscribedThemes(themeDtos);
        } else {
            dto.setSubscribedThemes(new ArrayList<>());
        }
        return dto;
    }
}
