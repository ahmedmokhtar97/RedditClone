package com.mokhtar.redditclone.service;


import com.mokhtar.redditclone.domain.User;
import com.mokhtar.redditclone.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User register(User user){
        return user;
    }

    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional
    public void Save(User... users){
        for(User user: users)
            userRepository.save(user);
    }

}
