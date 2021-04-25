package com.mokhtar.redditclone;

import com.mokhtar.redditclone.domain.Comment;
import com.mokhtar.redditclone.domain.Link;
import com.mokhtar.redditclone.repository.CommentRepository;
import com.mokhtar.redditclone.repository.LinkRepository;
import org.ocpsoft.prettytime.PrettyTime;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

@SpringBootApplication
@EnableTransactionManagement
public class RedditCloneApplication {

    private static final Logger logger = LoggerFactory.getLogger(RedditCloneApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RedditCloneApplication.class, args);
    }

    @Bean
    PrettyTime prettyTime() {
        return new PrettyTime();
    }


    @Bean
    public SpringSecurityDialect securityDialect(){
        return new SpringSecurityDialect();
    }

}
