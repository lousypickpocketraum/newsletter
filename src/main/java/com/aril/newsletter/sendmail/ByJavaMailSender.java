package com.aril.newsletter.sendmail;

import com.aril.newsletter.services.MailLogService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class ByJavaMailSender {

    private final String FROM = "yeness.drsn@gmail.com";

    private final JavaMailSender sender;

    public ByJavaMailSender(JavaMailSender sender) {
        this.sender = sender;
    }

    public void sendMail(MailObject mailObject) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        //set mediaType
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

        //add attachment
        for (String fileName : mailObject.getAttachments()) {
            helper.addAttachment(fileName, new ClassPathResource("chesterbennington.jpg"));
        }

        helper.setFrom(FROM);
        helper.setText(mailObject.getContent(), true);
        helper.setSubject(mailObject.getSubject());
        helper.setTo(mailObject.getTo());
        sender.send(message);
    }
}
