package com.openclassrooms.mddapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "commentaries")
@EntityListeners(AuditingEntityListener.class)
public class Commentary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="author_id",referencedColumnName = "id")
    private User author;

    private String message;

    @CreatedDate
    @Column(name="created_at", updatable = false)
    private LocalDateTime  createdAt;

    @LastModifiedDate
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
