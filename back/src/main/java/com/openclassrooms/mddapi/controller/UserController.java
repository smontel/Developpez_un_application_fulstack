package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.DTO.auth.UserDTO;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    @Operation(summary = "Profil d'un utilisateur", description = "Récupère les informations de d'un utilisateur en utilisant son ID")
    @SecurityRequirement(name = "bearerAuth")
    public UserDTO getUser(@PathVariable Long id){
        User user = userService.getUser(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));;
        return userMapper.toDTO(user);
    }


}
