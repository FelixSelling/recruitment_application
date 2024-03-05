package kth.iv1201.group9.recruitment_application.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * This class represents an email service that is responsible for sending
 * emails.
 */
@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    /**
     * Sends an email asynchronously.
     *
     * @param to      the recipient's email address
     * @param subject the subject of the email
     * @param body    the body of the email
     */
    @Async
    public void sendEmail(String to, String subject, String body, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(System.getenv("KTH_USERNAME") + "@kth.se");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }
}
