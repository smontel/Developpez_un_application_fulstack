package com.openclassrooms.mddapi.service;


import com.openclassrooms.mddapi.model.User;
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

    public Optional<User> getUserByMail(final String email) { return userRepository.findByEmail(email); }

    public Optional<User> getUser(final Long id) { return userRepository.findById(id); }

    public Iterable<User> getUsers() {return userRepository.findAll(); }

}
