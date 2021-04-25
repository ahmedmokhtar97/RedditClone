package com.mokhtar.redditclone.service;

import com.mokhtar.redditclone.domain.Comment;
import com.mokhtar.redditclone.repository.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final Logger  logger = LoggerFactory.getLogger(CommentService.class);

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }


}
