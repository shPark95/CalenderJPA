package org.server.calendarjpa.domain.user.dto;

import lombok.Getter;
import org.server.calendarjpa.domain.user.entity.User;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private final Long id;
    private final String username;
    private final String email;
    private final LocalDateTime createdDate;
    private final LocalDateTime lastModifiedDate;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.createdDate = user.getCreatedDate();
        this.lastModifiedDate = user.getLastModifiedDate();
    }
}
