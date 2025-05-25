package org.server.calendarjpa.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequestDto {
    @NotBlank(message = "유저명은 필수입니다")
    @Size(max = 4, message = "유저명은 최대 4글자 이내여야 합니다")
    private String username;
    @NotBlank(message="비밀번호는 필수입니다")
    private String password;
    @NotBlank(message="이메일은 필수입니다")
    @Pattern(
            regexp = "^[A-Za-z0-9]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "이메일 형식 오류"
    )
    private String email;
}
