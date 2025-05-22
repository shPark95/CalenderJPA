package org.server.calendarjpa.domain.user.service;

import jakarta.persistence.EntityNotFoundException;
import org.server.calendarjpa.domain.user.dto.UserRequestDto;
import org.server.calendarjpa.domain.user.dto.UserResponseDto;
import org.server.calendarjpa.domain.user.entity.User;
import org.server.calendarjpa.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto create(UserRequestDto userRequestDto) {
        User user = User.of(userRequestDto.getUsername(), userRequestDto.getPassword(), userRequestDto.getEmail());
        return new UserResponseDto(userRepository.save(user));
    }

    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        return new UserResponseDto(user);
    }

    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        User user = userRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        user = User.of(userRequestDto.getUsername(), userRequestDto.getPassword(), userRequestDto.getEmail());
        userRepository.save(user);
        return new UserResponseDto(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
