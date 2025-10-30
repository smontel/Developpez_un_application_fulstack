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



    //Créer un nouvel article
    public ArticleDetailDTO create(ArticleCreateDTO dto, UserDetails userDetails) {
        String email = userDetails.getUsername();
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

        Article savedArticle = articleRepository.save(article);

        return articleMapper.toDetailDto(savedArticle);
    }

    //Récupère un article détaillé ( avec les commentaires et les informations de l'auteur)
    @Transactional(readOnly = true)
    public ArticleDetailDTO findDetailById(Long id) {
        Article article = articleRepository.getReferenceById(id);
        return articleMapper.toDetailDto(article);
    }

    @Transactional(readOnly = true)
    public List<ArticleListDTO> findAll() {
        List<Article> articles = articleRepository.findAll();
        return articleMapper.toListDtoList(articles);
    }


    // ========== Feed personnalisé ==========


//    @Transactional(readOnly = true)
//    public Page<ArticleListDTO> getPersonalizedFeed(UserDetails userDetails, Pageable pageable) {
//
//        User user = getUserByEmail(userDetails.getUsername());
//
//        List<Long> subscribedThemeIds = user.getSubscribedThemes()
//                .stream()
//                .map(Theme::getId)
//                .collect(Collectors.toList());
//
//        if (subscribedThemeIds.isEmpty()) {
//            return Page.empty(pageable);
//        }
//
//        Page<Article> articles = articleRepository.findByThemesIdIn(subscribedThemeIds, pageable);
//
//        return articles.map(articleMapper::toListDto);
//    }
//
//    // ========== Méthodes utilitaires ==========
//
//    @Transactional(readOnly = true)
//    public boolean existsById(Long id) {
//        return articleRepository.existsById(id);
//    }
//
//    @Transactional(readOnly = true)
//    public long count() {
//        return articleRepository.count();
//    }
//
//
//    @Transactional(readOnly = true)
//    public Page<ArticleListDTO> findByTheme(Long themeId, Pageable pageable) {
//        Page<Article> articles = articleRepository.findByThemesId(themeId, pageable);
//        return articles.map(articleMapper::toListDto);
//    }
//
//    // ========== Méthodes privées (helpers) ==========
//
//
//    private Article getArticleById(Long id) {
//        return articleRepository.findById(id)
//                .orElseThrow(() -> new ArticleNotFoundException(
//                        "Article non trouvé avec l'id : " + id
//                ));
//    }
//
//    private User getUserByEmail(String email) {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException(
//                        "Utilisateur non trouvé avec l'email : " + email
//                ));
//    }
//
//    private void checkOwnership(Article article, UserDetails userDetails) {
//        if (!article.getAuthor().getEmail().equals(userDetails.getUsername())) {
//            throw new UnauthorizedException(
//                    "Vous n'êtes pas autorisé à effectuer cette action sur cet article"
//            );
//        }
   // }
}