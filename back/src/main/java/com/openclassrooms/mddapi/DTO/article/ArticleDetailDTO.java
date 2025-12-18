package com.openclassrooms.mddapi.DTO.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.openclassrooms.mddapi.DTO.commentary.CommentaryDTO;
import com.openclassrooms.mddapi.DTO.ThemeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDetailDTO {
    private Long id;

    private String title;

    private String content;

    @JsonProperty("themes")
    private List<ThemeDTO> themes;

    @JsonProperty("author_name")
    private String authorName;

    private List<CommentaryDTO> commentaries;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

}
