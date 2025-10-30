package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.DTO.ThemeDTO;
import com.openclassrooms.mddapi.service.ThemeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/themes")
@Tag(name="Themes", description = "Endpoints pour les themes")
public class ThemeController {
    @Autowired
    private ThemeService themeService;

    @GetMapping("")
    public List<ThemeDTO> getThemes(){
        return themeService.getThemes();
    }


}
