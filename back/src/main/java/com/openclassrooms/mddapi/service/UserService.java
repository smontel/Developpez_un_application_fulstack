package com.openclassrooms.mddapi.service;


import com.openclassrooms.mddapi.DTO.ThemeDTO;
import com.openclassrooms.mddapi.DTO.auth.UserDTO;
import com.openclassrooms.mddapi.DTO.auth.UserUpdateDTO;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.mapper.UserMapper;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @Autowired
    private ThemeMapper themeMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;


    public Optional<User> getUserByMail(final String email) { return userRepository.findByEmail(email); }

    public Optional<User> getUser(final Long id) { return userRepository.findById(id); }

    public Iterable<User> getUsers() {return userRepository.findAll(); }

    public String subscribeToTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Theme not found with id: " + themeId));

        String response;
        if (user.getSubscribedThemes() != null && user.getSubscribedThemes().contains(theme)) {
            user.getSubscribedThemes().remove(theme);
            response = "Désinscription avec succès";
        }
        else{
            user.getSubscribedThemes().add(theme);
            response = "Inscription avec succès";
        }

        userRepository.save(user);

        return response;
    }

    public UserDTO updateUser(Long id, UserUpdateDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if (dto.getName() != null && !dto.getName().isBlank()) {
            user.setName(dto.getName());
        }
        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        userRepository.save(user);
        return userMapper.toDTO(user);
    }

}
