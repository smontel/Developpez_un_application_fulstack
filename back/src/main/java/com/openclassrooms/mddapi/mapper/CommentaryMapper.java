package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.DTO.commentary.CommentaryDTO;
import com.openclassrooms.mddapi.model.Commentary;
import org.springframework.stereotype.Component;

@Component
public class CommentaryMapper {

    public CommentaryDTO toDTO(Commentary commentary) {
        if (commentary == null) {
            return null;
        }

        CommentaryDTO dto = new CommentaryDTO();
        dto.setId(commentary.getId());
        dto.setMessage(commentary.getMessage());
        dto.setCreatedAt(commentary.getCreatedAt());


        if (commentary.getAuthor() != null) {
            dto.setAuthorName(commentary.getAuthor().getName());
        }

        return dto;
    }

    public Commentary toEntity(CommentaryDTO dto) {
        if (dto == null) {
            return null;
        }

        Commentary commentary = new Commentary();
        commentary.setId(dto.getId());
        commentary.setMessage(dto.getMessage());

        return commentary;
    }
}