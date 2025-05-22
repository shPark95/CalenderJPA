package org.server.calendarjpa.domain.user.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {
    private String username;
    private String password;
    private String email;
}
