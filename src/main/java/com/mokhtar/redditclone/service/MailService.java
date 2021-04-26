package com.mokhtar.redditclone.service;

import com.mokhtar.redditclone.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import org.thymeleaf.context.Context;
import java.util.Locale;

@Service
public class MailService {

    private SpringTemplateEngine templateEngine;
    private JavaMailSender javaMailSender;
    private Logger logger = LoggerFactory.getLogger(MailService.class);
    private final String BASE_URL = "http://localhost:8080";

    public MailService(SpringTemplateEngine springTemplateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = springTemplateEngine;
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(String to, String subject, String content, boolean isMultiPart, boolean isHtml){
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
            message.setTo(to);
            message.setFrom("noreply@redditClone.com");
            message.setSubject(subject);
            message.setText(content, isHtml);
            javaMailSender.send(mimeMessage);
        } catch (Exception ex){
            logger.warn("Email Couldn't be sent to user'{}': {}", to, ex.getMessage());
        }
    }

    @Async
    public void sendEmailFromTemplate(User user, String templateName, String subject){
        Locale locale = Locale.ENGLISH;
        Context context = new Context(locale);
        context.setVariable("user", user);
        context.setVariable("baseURL", BASE_URL);
        String content = templateEngine.process(templateName, context);
        sendEmail(user.getEmail(), subject, content, false, true);
    }

    @Async
    public void sendActivationEmail(User user){
        sendEmailFromTemplate(user, "email/activation", "RedditClone User Activation");
    }

    @Async
    public void sendWelcomeEmail(User user){
        sendEmailFromTemplate(user, "email/activation", "Welcome to RedditClone");
    }
}
