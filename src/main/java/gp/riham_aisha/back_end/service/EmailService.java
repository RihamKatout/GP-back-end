package gp.riham_aisha.back_end.service;

import gp.riham_aisha.back_end.dto.admin.EmailDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final UserService userService;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(EmailDto emailDto) {
        String to = userService.getUser(emailDto.toId()).getEmail();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(emailDto.subject());
        message.setText(emailDto.text());
        message.setFrom(from);
        mailSender.send(message);
    }
}
