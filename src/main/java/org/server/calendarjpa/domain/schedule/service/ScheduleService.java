package org.server.calendarjpa.domain.schedule.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.server.calendarjpa.domain.schedule.dto.ScheduleRequestDto;
import org.server.calendarjpa.domain.schedule.dto.ScheduleResponseDto;
import org.server.calendarjpa.domain.schedule.entity.Schedule;
import org.server.calendarjpa.domain.schedule.repository.ScheduleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Page<ScheduleResponseDto> getPagedSchedules(int page, int size) {
        if (size <= 0) size = 6;
        Pageable pageable = PageRequest.of(page, size);
        Page<Schedule> schedulesPage = scheduleRepository.findAllByOrderByLastModifiedDateDesc(pageable);

        return schedulesPage.map(ScheduleResponseDto::new);
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
