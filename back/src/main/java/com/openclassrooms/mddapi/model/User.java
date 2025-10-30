package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@Data
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @Size(max = 20)
    private String name;

    @NonNull
    @Size(max = 50)
    @Email
    private String email;

    @NonNull
    private String password;

    @OneToMany(mappedBy = "author")
    private List<Commentary> commentaries = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_theme",
                joinColumns = @JoinColumn(name="user_id"),
                inverseJoinColumns = @JoinColumn(name="theme_id"))
    private List<Theme> subscribedThemes = new ArrayList<>();

    @CreatedDate
    @Column(name="created_at", updatable = false)
    private LocalDate createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDate updatedAt;


}