package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.DTO.article.ArticleCreateDTO;
import com.openclassrooms.mddapi.DTO.article.ArticleDetailDTO;
import com.openclassrooms.mddapi.DTO.article.ArticleListDTO;
import com.openclassrooms.mddapi.DTO.CommentaryDTO;
import com.openclassrooms.mddapi.DTO.ThemeDTO;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Commentary;
import com.openclassrooms.mddapi.model.Theme;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ArticleMapper {


    public ArticleListDTO toListDto(Article article) {
        if (article == null) {
            return null;
        }

        ArticleListDTO dto = new ArticleListDTO();

        dto.setId(article.getId());
        dto.setTitle(article.getTitle());
        dto.setCreatedAt(article.getCreatedAt());

        if (article.getAuthor() != null) {
            dto.setAuthorName(article.getAuthor().getName());
        }

        if (article.getThemes() != null && !article.getThemes().isEmpty()) {
            List<String> themeNames = article.getThemes()
                    .stream()
                    .map(Theme::getName)
                    .collect(Collectors.toList());
            dto.setThemeNames(themeNames);
        } else {
            dto.setThemeNames(new ArrayList<>());
        }

        if (article.getCommentaries() != null) {
            dto.setCommentaryCount(article.getCommentaries().size());
        } else {
            dto.setCommentaryCount(0);
        }

        return dto;
    }

    public ArticleDetailDTO toDetailDto(Article article) {
        if (article == null) {
            return null;
        }

        ArticleDetailDTO dto = new ArticleDetailDTO();

        dto.setId(article.getId());
        dto.setTitle(article.getTitle());
        dto.setContent(article.getContent());
        dto.setCreatedAt(article.getCreatedAt());
        dto.setUpdatedAt(article.getUpdatedAt());

        if (article.getAuthor() != null) {
            dto.setAuthorName(article.getAuthor().getName());
        }

        if (article.getThemes() != null && !article.getThemes().isEmpty()) {
            List<ThemeDTO> themeDtos = article.getThemes()
                    .stream()
                    .map(theme -> {
                        ThemeDTO iterationDTO = new ThemeDTO();
                        iterationDTO.setName(theme.getName());
                        return iterationDTO;
                    })
                    .collect(Collectors.toList());
            dto.setThemes(themeDtos);
        } else {
            dto.setThemes(new ArrayList<>());
        }

        if (article.getCommentaries() != null && !article.getCommentaries().isEmpty()) {
            List<CommentaryDTO> commentaryDtos = article.getCommentaries()
                    .stream()
                    .map(this::toCommentaryDto)
                    .collect(Collectors.toList());
            dto.setCommentaries(commentaryDtos);
        } else {
            dto.setCommentaries(new ArrayList<>());
        }

        return dto;
    }

    private CommentaryDTO toCommentaryDto(Commentary commentary) {
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


    public Article toEntity(ArticleCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());

        return article;
    }


    public List<ArticleListDTO> toListDtoList(List<Article> articles) {
        if (articles == null) {
            return new ArrayList<>();
        }

        return articles.stream()
                .map(this::toListDto)
                .collect(Collectors.toList());
    }
}