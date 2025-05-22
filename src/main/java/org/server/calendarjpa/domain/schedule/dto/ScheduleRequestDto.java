package org.server.calendarjpa.domain.schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
    private String title;
    private String description;

    public ScheduleRequestDto() {}

}
