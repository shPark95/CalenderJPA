package org.server.calendarjpa.domain.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.server.calendarjpa.domain.comment.entity.Comment;
import org.server.calendarjpa.domain.schedule.entity.Schedule;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "user")
    private List<Schedule> schedules = new ArrayList<>();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
    private String username;
    private String password;
    private String email;
    @CreatedDate @Column(updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    protected User() {}

    private User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public static User of(String username, String password, String email) {
        return new User(username, password, email);
    }
}