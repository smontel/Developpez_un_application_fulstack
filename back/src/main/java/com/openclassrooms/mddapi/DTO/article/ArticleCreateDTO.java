package com.openclassrooms.mddapi.DTO.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCreateDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotEmpty
    @JsonProperty("theme_ids")
    private List<Long> themeIds;
}