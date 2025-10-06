package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Entity
@Table(name="db_user")
@Data
@EntityListeners(AuditingEntityListener.class)
public class DBUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String password;

    @CreatedDate
    @Column(name="created_at", updatable = false)
    private LocalDate createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDate updatedAt;
}