package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.DTO.article.ArticleCreateDTO;
import com.openclassrooms.mddapi.DTO.article.ArticleDetailDTO;
import com.openclassrooms.mddapi.DTO.article.ArticleListDTO;
import com.openclassrooms.mddapi.mapper.ArticleMapper;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.ArticleService;
import com.openclassrooms.mddapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    private final UserService userService;

    private final ArticleMapper articleMapper;


    @PostMapping("")
    public ResponseEntity<String> createArticle(@Valid @RequestBody ArticleCreateDTO articleCreateDto, Principal principal) {
        User user = userService.getUserByMail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        articleService.create(articleCreateDto, user);

        return ResponseEntity.ok("Article créé avec succès");
    }

    @GetMapping("")
    @Operation(summary = "", description = "Récupère tout les articles en base de donnée")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<List<ArticleListDTO>> getAllArticles( ) {
        List<Article> articleList = articleService.findAll();
        return ResponseEntity.ok(articleMapper.toListDtoList(articleList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDetailDTO> getArticleById(@PathVariable Long id) {
        Article article = articleService.findDetailById(id);
        return ResponseEntity.ok(articleMapper.toDetailDto(article));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateArticle(@PathVariable Long id, @Valid @RequestBody ArticleCreateDTO articleCreateDTO, Principal principal) {
        User user = userService.getUserByMail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Article article = articleService.findDetailById(id);
        if(article.getAuthor().getId().equals(user.getId())){
            articleService.update(id, articleCreateDTO);
            return ResponseEntity.ok("Article modifié avec succès");
        }
        else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Vous n'êtes pas autorisé à modifier cet article");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id, Principal principal) {
        User user = userService.getUserByMail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Article article = articleService.findDetailById(id);

        if(article.getAuthor().getId().equals(user.getId())) {
            articleService.deleteArticle(id);
            return ResponseEntity.ok("Article supprimé avec succès");
        }
        else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Vous n'êtes pas autorisé à supprimer cet article");
        }
    }

    @GetMapping("/feed")
    public ResponseEntity<List<ArticleListDTO>> getPersonalizedFeed( Principal principal) {
        User user = userService.getUserByMail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return ResponseEntity.ok(articleService.getPersonalizedFeed(user));
    }


}
