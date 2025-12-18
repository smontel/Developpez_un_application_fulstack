package com.openclassrooms.mddapi.controller;


import com.openclassrooms.mddapi.DTO.MessageResponseDTO;
import com.openclassrooms.mddapi.DTO.commentary.CommentaryRequest;
import com.openclassrooms.mddapi.mapper.CommentaryMapper;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.service.ArticleService;
import com.openclassrooms.mddapi.service.CommentaryService;
import com.openclassrooms.mddapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class CommentaryController {

    @Autowired
    CommentaryService commentaryService;
    @Autowired
    UserService userService;
    @Autowired
    ArticleService articleService;
    @Autowired
    CommentaryMapper commentaryMapper;

    @PostMapping("/messages")
    @Operation(summary = "Profil utilisateur", description = "Récupère les informations de l'utilisateur connecté")
    @SecurityRequirement(name = "bearerAuth")
    public MessageResponseDTO posMessage(@RequestBody CommentaryRequest request, Principal principal){
        User user = userService.getUserByMail(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Article article = articleService.findDetailById(request.getArticleId());
        commentaryService.saveMessage(request, user, article);
        MessageResponseDTO messageResponse = new MessageResponseDTO();
        messageResponse.setMessage("Message send with success!");
        return messageResponse;
    }
}
