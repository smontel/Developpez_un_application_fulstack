package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.DTO.ThemeDTO;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private ThemeMapper themeMapper;

    public List<ThemeDTO> getThemes() {
        List<Theme> themes = themeRepository.findAll();
        return themes.stream()
                .map(themeMapper::toDto)
                .collect(Collectors.toList());
    }


}
