package org.server.calendarjpa.domain.user.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @CreatedDate @Column(updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    protected User() {}

    private User(String username, String password, String email, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}