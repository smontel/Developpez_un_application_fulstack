package com.openclassrooms.mddapi.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CommentaryDTO {

    private Long id;

    private String message;

    private String authorName;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    
}
