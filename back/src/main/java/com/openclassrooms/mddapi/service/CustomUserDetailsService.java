package com.openclassrooms.mddapi.service;


import com.openclassrooms.mddapi.model.DBUser;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  BCryptPasswordEncoder passwordEncoder;


    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println(email);
        DBUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable avec l’email : " + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }


    public boolean comparePasswords(String email, String password) {
        UserDetails user = loadUserByUsername(email);
        System.out.println(user);
        return passwordEncoder.matches(password, user.getPassword());
    }

    public boolean registerUser(String email, String password, String name){

        if (userRepository.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email déjà utilisé");
        }
        DBUser newUser = new DBUser();
        newUser.setEmail(email);
        newUser.setName(name);
        String hashedPassword = passwordEncoder.encode(password);
        newUser.setPassword(hashedPassword);

        // Dates
        LocalDate now = LocalDate.now();
        newUser.setCreatedAt(now);
        newUser.setUpdatedAt(now);

        userRepository.save(newUser);
        return true;
    }

}
