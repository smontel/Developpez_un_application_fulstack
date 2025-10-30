package com.openclassrooms.mddapi.DTO.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleListDTO {

    private Long id;

    private String title;

    @JsonProperty("author_name")
    private String authorName;

    @JsonProperty("theme_names")
    private List<String> themeNames;

    @JsonProperty("commentary_count")
    private Integer commentaryCount;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}