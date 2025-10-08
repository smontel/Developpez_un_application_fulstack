package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;

public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @OneToOne
    @JoinColumn(name = "theme_id", referencedColumnName = "id")
    private List<Theme> themes;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User author;

    @CreatedDate
    @Column(name="created_at", updatable = false)
    private LocalDate createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDate updatedAt;

}
