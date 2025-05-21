package org.server.calendarjpa.domain.comment.service;

import org.server.calendarjpa.domain.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
