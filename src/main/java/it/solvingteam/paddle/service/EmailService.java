package it.solvingteam.paddle.service;

import java.util.stream.Stream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;
    
    @Value("${mail.from}")
    private String from;

    public void sendEmail(String to, String subject, String text) {

        try {

            MimeMessage mimeMessage = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(from);

            //destinatari multipli
            Stream.of(to.split(",")).forEach(dest -> {
                try {
                    helper.addTo(dest);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            });

            helper.setSubject(subject);
            helper.setText(text);

            sender.send(mimeMessage);
            System.out.println("Email inviata");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
