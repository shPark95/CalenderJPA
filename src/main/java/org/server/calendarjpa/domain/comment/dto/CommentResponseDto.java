package org.server.calendarjpa.domain.comment.dto;

import lombok.Getter;
import org.server.calendarjpa.domain.comment.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String description;
    private final LocalDateTime createdDate;
    private final LocalDateTime lastModifiedDate;
    private final Long userId;
    private final Long scheduleId;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.description = comment.getDescription();
        this.createdDate = comment.getCreatedDate();
        this.lastModifiedDate = comment.getLastModifiedDate();
        this.userId = comment.getUser().getId();
        this.scheduleId = comment.getSchedule().getId();
    }
}
