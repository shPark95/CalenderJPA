package org.server.calendarjpa.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String description;

    public CommentRequestDto() {}
}
