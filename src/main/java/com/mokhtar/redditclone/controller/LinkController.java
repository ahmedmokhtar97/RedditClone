package com.mokhtar.redditclone.controller;


import com.mokhtar.redditclone.domain.Link;
import com.mokhtar.redditclone.repository.LinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private LinkRepository linkRepository;

    private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

    @Autowired
    public LinkController(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @GetMapping("/")
    public String list(Model model){
        model.addAttribute("pageTitle", "Link");
        List<Link> links = linkRepository.findAll();
        model.addAttribute("links", links);
        return "link/index";
    }


    @GetMapping("link/{id}")
    public String getById(@PathVariable Long id, Model model){
        Optional<Link> link = linkRepository.findById(id);
        if(link.isPresent()) {
            model.addAttribute("link", link.get());
            return "link/view";
        }       else
            return "redirect:/";
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
            linkRepository.save(link);
            logger.info("LINK SAVED SUCCESSFULLY");
            redirectAttributes
                    .addAttribute("id", link.getId())
                    .addFlashAttribute("success", true);
            return "redirect:/link/{id}";
        }
    }

}

