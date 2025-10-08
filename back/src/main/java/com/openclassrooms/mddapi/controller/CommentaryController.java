package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.DTO.CommentaryDTO;
import com.openclassrooms.mddapi.mapper.CommentaryMapper;
import com.openclassrooms.mddapi.service.CommentaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentaryController {

    @Autowired
    CommentaryService commentaryService;
    @Autowired
    CommentaryMapper commentaryMapper;

//    @PostMapping("/messages")
//    @Operation(summary = "Profil utilisateur", description = "Récupère les informations de l'utilisateur connecté")
//    @SecurityRequirement(name = "bearerAuth")
//    public CommentaryDTO posMessage(@RequestBody CommentaryDTO messageRequest){
//        return CommentaryService.saveMessage(commentaryMapper.toEntity(messageRequest));
//    }
}
