package com.openclassrooms.mddapi.DTO.commentary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentaryRequest {

    @NotNull
    private Long articleId;

    @NotBlank
    @Size(max = 2000)
    private String message;
}
