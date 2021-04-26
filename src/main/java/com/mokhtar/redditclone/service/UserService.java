package com.mokhtar.redditclone.service;


import com.mokhtar.redditclone.domain.User;
import com.mokhtar.redditclone.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserRepository userRepository;
    private MailService mailService;
    private RoleService roleService;
    private BCryptPasswordEncoder encoder;


    public UserService(UserRepository userRepository, MailService mailService, RoleService roleService) {
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.roleService = roleService;
        encoder = new BCryptPasswordEncoder();
    }

    public User register(User user){
        String secret = "{bcrypt}"+encoder.encode(user.getPassword());
        user.setPassword(secret);
        user.setConfirmPassword(secret);
        user.addRole(roleService.findByName("ROLE_USER"));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setEnabled(false);
        sendActivationEmail(user);
        return user;

    }

    public void sendActivationEmail(User user) {
        mailService.sendActivationEmail(user);
    }

    public void sendWelcomeEmail(User user){
        mailService.sendWelcomeEmail(user);
    }
    public User save(User user){
        return userRepository.save(user);
    }

    @Transactional
    public void Save(User... users){
        for(User user: users)
            userRepository.save(user);
    }

    public Optional<User> findByEmailAndActivationCode(String email, String activationCode){
        return userRepository.findByEmailAndActivationCode(email, activationCode);
    }


}
