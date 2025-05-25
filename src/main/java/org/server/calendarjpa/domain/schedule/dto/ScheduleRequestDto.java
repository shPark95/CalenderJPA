package org.server.calendarjpa.domain.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    @NotBlank(message = "제목은 필수입니다")
    @Size(max =10, message= "제목은 10글자 이내여야 합니다")
    private String title;
    private String description;

    public ScheduleRequestDto() {}

}
