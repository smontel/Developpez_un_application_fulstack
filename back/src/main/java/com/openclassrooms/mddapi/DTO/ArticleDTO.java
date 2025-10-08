package com.openclassrooms.mddapi.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class ArticleDTO {
    private long id;

    private String title;

    private String content;

    private List<String> themes;

    private UserDTO author;

    private List<CommentaryDTO> commentaries;

    @JsonProperty("created_at")
    private LocalDate createdAt;

    @JsonProperty("updated_at")
    private LocalDate updatedAt;

}
