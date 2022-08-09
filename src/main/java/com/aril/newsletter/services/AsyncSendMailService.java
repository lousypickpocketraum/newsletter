package com.aril.newsletter.services;

import com.aril.newsletter.constants.MailLogStatus;
import com.aril.newsletter.payloads.response.MailAttachmentResponse;
import com.aril.newsletter.payloads.response.MailLogResponse;
import com.aril.newsletter.payloads.response.MailTemplateResponse;
import com.aril.newsletter.payloads.response.SendMailResponse;
import com.aril.newsletter.repositories.IMailLogRepository;
import com.aril.newsletter.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class AsyncSendMailService implements IAsyncSendMailService {

    @Autowired
    private JavaMailSender sender;
    @Autowired
    private MailTemplateService mailTemplateService;

    @Override
    public MailLogResponse sendMail(String mailAddress, Long mailTemplateId, Map<String, Object> model) {
        MailTemplateResponse mailTemplateResponse = mailTemplateService.findById(mailTemplateId);
        MimeMessage message = sender.createMimeMessage();
        MailLogResponse response = new MailLogResponse();
        try {
            //set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            //add attachment
            for(MailAttachmentResponse attachment: mailTemplateResponse.getAttachments()) {
                helper.addAttachment(attachment.getName(), new ClassPathResource("chesterbennington.jpg"));
            }
            String html = Util.insertParams(mailTemplateResponse.getContent(), model);
            helper.setFrom("yeness.drsn@gmail.com");
            helper.setText(html, true);
            helper.setSubject(mailTemplateResponse.getName());
            helper.setTo(mailAddress);
            sender.send(message);
            response.setStatus(MailLogStatus.SUCCESS);
        }catch (MessagingException e){
            response.setStatus(MailLogStatus.FAIL);
        }
        return response;
    }
}
