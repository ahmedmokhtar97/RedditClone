package com.mokhtar.redditclone.controller;


import com.mokhtar.redditclone.domain.Link;
import com.mokhtar.redditclone.domain.Vote;
import com.mokhtar.redditclone.repository.LinkRepository;
import com.mokhtar.redditclone.repository.VoteRepository;
import com.mokhtar.redditclone.service.LinkService;
import com.mokhtar.redditclone.service.VoteService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class VoteController {

    private LinkService linkService;
    private VoteService voteService;

    public VoteController(LinkService linkService, VoteService voteService) {
        this.linkService = linkService;
        this.voteService = voteService;
    }


    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/vote/link/{linkId}/direction/{direction}/votecount/{voteCount}")
    public int vote(@PathVariable Long linkId, @PathVariable short direction, @PathVariable int voteCount ){
        Optional<Link> optionalLink = linkService.findById(linkId);
        if(optionalLink.isPresent()){
            Link link = optionalLink.get();
            Vote vote = new Vote(direction, link);
            voteService.save(vote);

            int updateVoteCount = voteCount + direction;
            link.setVoteCount(updateVoteCount);
            linkService.save(link);

            return updateVoteCount;
        }
        return voteCount;
    }


}
