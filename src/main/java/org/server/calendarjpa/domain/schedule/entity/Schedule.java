package org.server.calendarjpa.domain.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.server.calendarjpa.domain.user.entity.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "schedules")
@EntityListeners(AuditingEntityListener.class)
public class Schedule {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    private String title;
    private String description;
    @CreatedDate @Column(updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    protected Schedule() {}

    private Schedule(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public static Schedule of(String title, String description) {
        return new Schedule(title, description);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
