package com.mokhtar.redditclone.repository;

import com.mokhtar.redditclone.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
