package org.server.calendarjpa.domain.user.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.server.calendarjpa.config.PasswordEncoder;
import org.server.calendarjpa.domain.user.dto.UserRequestDto;
import org.server.calendarjpa.domain.user.dto.UserResponseDto;
import org.server.calendarjpa.domain.user.entity.User;
import org.server.calendarjpa.domain.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto login(UserRequestDto userRequestDto, HttpServletRequest httpServletRequest) {
        User user = userRepository.findByEmail(userRequestDto.getEmail()).orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "사용자 정보가 일치하지 않습니다."));

        if (!passwordEncoder.matches(userRequestDto.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "사용자 정보가 일치하지 않습니다.");
        }

        httpServletRequest.getSession().setAttribute("user", user.getId());

        return new UserResponseDto(user);
    }

    public UserResponseDto signup(UserRequestDto userRequestDto) {
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(userRequestDto.getPassword());

        User user = User.of(userRequestDto.getUsername(), encodedPassword, userRequestDto.getEmail());
        return new UserResponseDto(userRepository.save(user));
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
