package org.server.calendarjpa.domain.schedule.service;

import jakarta.persistence.EntityNotFoundException;
import org.server.calendarjpa.domain.schedule.dto.ScheduleRequestDto;
import org.server.calendarjpa.domain.schedule.dto.ScheduleResponseDto;
import org.server.calendarjpa.domain.schedule.entity.Schedule;
import org.server.calendarjpa.domain.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto create(ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = Schedule.of(scheduleRequestDto.getTitle(), scheduleRequestDto.getDescription());
        return new ScheduleResponseDto(scheduleRepository.save(schedule));
    }

    public List<ScheduleResponseDto> findAll() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleResponseDto::new)
                .collect(Collectors.toList());
    }

    public ScheduleResponseDto findById(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));
        return new ScheduleResponseDto(schedule);
    }

    public ScheduleResponseDto update(Long id, ScheduleRequestDto scheduleRequestDto) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));

        schedule.setTitle(scheduleRequestDto.getTitle());
        schedule.setDescription(scheduleRequestDto.getDescription());
        return new ScheduleResponseDto(scheduleRepository.save(schedule));
    }

    public void deleteById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
