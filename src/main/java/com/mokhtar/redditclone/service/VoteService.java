package com.mokhtar.redditclone.service;

import com.mokhtar.redditclone.domain.Vote;
import com.mokhtar.redditclone.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private final Logger logger = LoggerFactory.getLogger(VoteService.class);
    private VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public Vote save(Vote vote){
        return voteRepository.save(vote);
    }


}
