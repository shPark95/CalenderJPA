package org.server.calendarjpa.domain.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.server.calendarjpa.domain.user.dto.UserRequestDto;
import org.server.calendarjpa.domain.user.dto.UserResponseDto;
import org.server.calendarjpa.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody UserRequestDto userRequestDto, HttpServletRequest httpServeletRequest) {
        return ResponseEntity.ok(userService.login(userRequestDto, httpServeletRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signup(@RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.signup(userRequestDto));
    }

    @PostMapping("/users")
    public UserResponseDto create(@RequestBody UserRequestDto userRequestDto) {
        return userService.create(userRequestDto);
    }

    @GetMapping("/users")
    public List<UserResponseDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/users/{id}")
    public UserResponseDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PatchMapping("/users/{id}")
    public UserResponseDto update(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return userService.update(id, userRequestDto);
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
