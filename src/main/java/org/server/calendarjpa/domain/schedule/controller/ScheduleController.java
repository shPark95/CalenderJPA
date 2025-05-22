package org.server.calendarjpa.domain.schedule.controller;

import org.server.calendarjpa.domain.schedule.dto.ScheduleRequestDto;
import org.server.calendarjpa.domain.schedule.dto.ScheduleResponseDto;
import org.server.calendarjpa.domain.schedule.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedules")
    public ScheduleResponseDto create(@RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.create(scheduleRequestDto);
    }

    @GetMapping("/schedules")
    public List<ScheduleResponseDto> findAll() {
        return scheduleService.findAll();
    }

    @GetMapping("/schedules/{id}")
    public ScheduleResponseDto findById(@PathVariable Long id) {
        return scheduleService.findById(id);
    }

    @PatchMapping("/schedules/{id}")
    public ScheduleResponseDto update(@PathVariable Long id, @RequestBody ScheduleRequestDto scheduleRequestDto) {
        return scheduleService.update(id, scheduleRequestDto);
    }

    @DeleteMapping("/schedules/{id}")
    public void deleteById(@PathVariable Long id) {
        scheduleService.deleteById(id);
    }
}
