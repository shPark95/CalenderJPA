package org.server.calendarjpa.domain.schedule.dto;

import lombok.Getter;
import org.server.calendarjpa.domain.schedule.entity.Schedule;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String description;
    private final LocalDateTime createdDate;
    private final LocalDateTime lastModifiedDate;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.description = schedule.getDescription();
        this.createdDate = schedule.getCreatedDate();
        this.lastModifiedDate = schedule.getLastModifiedDate();
    }
}
