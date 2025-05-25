package org.server.calendarjpa.domain.comment.service;

import jakarta.transaction.Transactional;
import org.server.calendarjpa.domain.comment.dto.CommentRequestDto;
import org.server.calendarjpa.domain.comment.dto.CommentResponseDto;
import org.server.calendarjpa.domain.comment.entity.Comment;
import org.server.calendarjpa.domain.comment.repository.CommentRepository;
import org.server.calendarjpa.domain.schedule.entity.Schedule;
import org.server.calendarjpa.domain.schedule.repository.ScheduleRepository;
import org.server.calendarjpa.domain.user.entity.User;
import org.server.calendarjpa.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private ScheduleRepository scheduleRepository;
    private UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    /*
    * 생성
    */
    @Transactional
    public CommentResponseDto createComment(Long scheduleId, Long userId, CommentRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found"));

        Comment comment = Comment.of(user, schedule, requestDto.getDescription());
        Comment savedComment = commentRepository.save(comment);
        return new CommentResponseDto(savedComment);
    }

    /*
    * 조회
    */
    public List<CommentResponseDto> getCommentsBySchedule(Long scheduleId) {
        List<Comment> comments = commentRepository.findByScheduleId(scheduleId);
        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    /*
    * 수정
    */
    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));

        comment.setDescription(requestDto.getDescription());
        return new CommentResponseDto(comment);
    }

    /*
    * 삭제
    */
    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
