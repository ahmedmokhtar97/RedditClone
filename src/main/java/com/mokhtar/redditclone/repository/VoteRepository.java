package com.mokhtar.redditclone.repository;

import com.mokhtar.redditclone.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
