package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.DTO.ThemeDTO;
import com.openclassrooms.mddapi.model.Theme;
import org.springframework.stereotype.Component;

@Component
public class ThemeMapper {

    public ThemeDTO toDto(Theme theme) {
        if (theme == null) {
            return null;
        }

        ThemeDTO dto = new ThemeDTO();
        dto.setId(theme.getId());
        dto.setName(theme.getName());

        return dto;
    }

}