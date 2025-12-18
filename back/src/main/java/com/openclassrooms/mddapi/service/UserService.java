package com.openclassrooms.mddapi.service;


import com.openclassrooms.mddapi.DTO.ThemeDTO;
import com.openclassrooms.mddapi.mapper.ThemeMapper;
import com.openclassrooms.mddapi.model.Theme;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ThemeRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
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


    public Optional<User> getUserByMail(final String email) { return userRepository.findByEmail(email); }

    public Optional<User> getUser(final Long id) { return userRepository.findById(id); }

    public Iterable<User> getUsers() {return userRepository.findAll(); }

    public void subscribeToTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new RuntimeException("Theme not found with id: " + themeId));

        if (user.getSubscribedThemes() != null && user.getSubscribedThemes().contains(theme)) {
            user.getSubscribedThemes().remove(theme);
        }
        else{
            user.getSubscribedThemes().add(theme);
        }

        userRepository.save(user);
    }

}
