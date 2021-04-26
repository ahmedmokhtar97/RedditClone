package com.mokhtar.redditclone.controller;


import com.mokhtar.redditclone.domain.Comment;
import com.mokhtar.redditclone.domain.Link;
import com.mokhtar.redditclone.repository.CommentRepository;
import com.mokhtar.redditclone.repository.LinkRepository;
import com.mokhtar.redditclone.service.CommentService;
import com.mokhtar.redditclone.service.LinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class LinkController {

    private LinkService linkService;
    private CommentService commentService;

    private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

    @Autowired
    public LinkController(LinkService linkService, CommentService commentService) {
        this.linkService =  linkService;
        this.commentService = commentService;
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("pageTitle", "Link");
        List<Link> links = linkService.findAll();
        model.addAttribute("links", links);
        return "link/index";
    }


    @GetMapping("/link/{id}")
    public String read(@PathVariable Long id, Model model) {
        Optional<Link> link = linkService.findById(id);
        if( link.isPresent() ) {
            Link currentLink = link.get();
            Comment comment = new Comment();
            comment.setLink(currentLink);

            logger.warn("User Alias: {}",currentLink.getUser().getAlias());
            model.addAttribute("comment",comment);
            model.addAttribute("link",currentLink);
            model.addAttribute("success", model.containsAttribute("success"));
            return "link/view";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("link/submit")
    public String getSubmitting(Model model){
        model.addAttribute("link", new Link());
        return "link/submit";
    }

    @PostMapping("link/submit")
    public String postSubmitting(@Valid Link link, BindingResult bindingResult,
                                 Model model, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            logger.info("Some Errors");
            model.addAttribute("link", link);
            return "link/submit";
        }else{

            linkService.save(link);
            logger.info("LINK SAVED SUCCESSFULLY");
            redirectAttributes
                    .addAttribute("id", link.getId())
                    .addFlashAttribute("success", true);
            return "redirect:/link/{id}";
        }
    }

    @Secured("ROLE_USER")
    @PostMapping("/link/comments")
    public String addComment(@Valid Comment comment, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            logger.error(bindingResult.toString());
        else{
            commentService.save(comment);
            logger.info("COMMENT IS SAVED");
        }
        return "redirect:/link/"+ comment.getLink().getId();
    }
}

