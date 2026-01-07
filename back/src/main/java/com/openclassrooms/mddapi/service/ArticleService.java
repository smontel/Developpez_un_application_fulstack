package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.DTO.article.ArticleCreateDTO;
import com.openclassrooms.mddapi.DTO.article.ArticleDetailDTO;
import com.openclassrooms.mddapi.DTO.article.ArticleListDTO;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final ArticleMapper articleMapper;


    public Article create(ArticleCreateDTO dto, User user) {
        String email = user.getEmail();
        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(
                        "Utilisateur non trouvé avec l'email : " + email
                ));;
        List<Theme> themes = themeRepository.findAllById(dto.getThemeIds());

        if (themes.size() != dto.getThemeIds().size()) {
            throw new IllegalArgumentException("Un ou plusieurs thèmes sont invalides");
        }

        Article article = articleMapper.toEntity(dto);

        article.setAuthor(author);
        article.setThemes(themes);

        return articleRepository.save(article);

    }

    public Article update(Long id, ArticleCreateDTO dto){
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Article non trouvé avec l'id : " + id
                ));
        List<Theme> themes = themeRepository.findAllById(dto.getThemeIds());

        if (themes.size() != dto.getThemeIds().size()) {
            throw new IllegalArgumentException("Un ou plusieurs thèmes sont invalides");
        }

        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        article.setThemes(themes);

        return articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public Article findDetailById(Long id) {
        return articleRepository.getReferenceById(id);
    }

    @Transactional(readOnly = true)
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public void deleteArticle( Long id ){ articleRepository.deleteById(id);}


    @Transactional(readOnly = true)
    public List<ArticleListDTO> getPersonalizedFeed(User user) {

        List<Long> subscribedThemeIds = user.getSubscribedThemes()
                .stream()
                .map(Theme::getId)
                .collect(Collectors.toList());

        if (subscribedThemeIds.isEmpty()) {
            return Collections.emptyList();
        }

        List<Article> articles = articleRepository.findByThemesIdIn(subscribedThemeIds);

        return articles.stream()
                .map(articleMapper::toListDto)
                .collect(Collectors.toList());
    }


}