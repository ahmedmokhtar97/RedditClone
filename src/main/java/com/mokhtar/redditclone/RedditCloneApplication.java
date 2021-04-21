package com.mokhtar.redditclone;

import com.mokhtar.redditclone.domain.Comment;
import com.mokhtar.redditclone.domain.Link;
import com.mokhtar.redditclone.repository.CommentRepository;
import com.mokhtar.redditclone.repository.LinkRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RedditCloneApplication {

    private static final Logger logger = LoggerFactory.getLogger(RedditCloneApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RedditCloneApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository){
        return args -> {
            Link link = new Link("Getting Started with Spring Boot 2","https://therealdanvega.com/spring-boot-2");
            linkRepository.save(link);

            Comment comment = new Comment("What an awesome idea for a course!", link);
            commentRepository.save(comment);
            link.addComment(comment);

            System.out.println("Saved items in H2 DB");



        };
    }

}
