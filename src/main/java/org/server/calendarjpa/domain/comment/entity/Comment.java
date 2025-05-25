package org.server.calendarjpa.domain.comment.entity;

import jakarta.persistence.*;
import jdk.jfr.Description;
import lombok.Getter;
import org.server.calendarjpa.domain.schedule.entity.Schedule;
import org.server.calendarjpa.domain.user.entity.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="schedule_id", nullable=false)
    private Schedule schedule;
    private String description;
    @CreatedDate @Column(updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    protected Comment() {}

    private Comment(User user, Schedule schedule, String description) {
        this.user = user;
        this.schedule = schedule;
        this.description = description;
    }

    public static Comment of(User user, Schedule schedule, String description) {
        return new Comment(user, schedule, description);
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
