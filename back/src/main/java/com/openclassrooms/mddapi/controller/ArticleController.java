package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.DTO.article.ArticleDetailDTO;
import com.openclassrooms.mddapi.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;
//
//
//    @PostMapping
//    public ResponseEntity<ArticleDetailDTO> createArticle(@Valid @RequestBody ArticleDetailDTO articleDetailDto) {}
//
//    @GetMapping
//    public ResponseEntity<List<ArticleDetailDTO>> getAllArticles( ) {}
//
//    @GetMapping("/{id}")
//    public ResponseEntity<ArticleDetailDTO> getArticleById(@PathVariable Long id) {}
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ArticleDetailDTO> updateArticle(@PathVariable Long id, @Valid @RequestBody ArticleDetailDTO articleDetailDto) {}
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {}
//
//    @GetMapping("/feed")
//    public ResponseEntity<List<ArticleDetailDTO>> getPersonalizedFeed(@AuthenticationPrincipal UserDetails userDetails) {}
//

}
