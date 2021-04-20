package com.mokhtar.redditclone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication

public class RedditCloneApplication {

    private static final Logger logger = LoggerFactory.getLogger(RedditCloneApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RedditCloneApplication.class, args);
    }


    @Bean
    CommandLineRunner runnerDev(){
        return args -> {
            logger.error("You are at Error level");
            logger.warn("You are at Warn level");
            logger.info("You are at Info level");
            logger.debug("You are at Debug level");
            logger.trace("You are at Trace level");
        };
    }


}
