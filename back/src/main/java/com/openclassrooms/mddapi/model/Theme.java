package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "SUBSCRIBED",
            joinColumns = @JoinColumn( name = "article_id" ),
            inverseJoinColumns = @JoinColumn( name = "user_id" ) )
    private List<User> users;
}
