package com.aril.newsletter.services;

import com.aril.newsletter.constants.MailLogStatus;
import com.aril.newsletter.entities.MailLog;
import com.aril.newsletter.payloads.request.SendMailRequest;
import com.aril.newsletter.payloads.response.MailAttachmentResponse;
import com.aril.newsletter.payloads.response.MailGroupResponse;
import com.aril.newsletter.payloads.response.MailLogResponse;
import com.aril.newsletter.payloads.response.MailTemplateResponse;
import com.aril.newsletter.repositories.IMailLogRepository;
import com.aril.newsletter.sendmail.ByJavaMailSender;
import com.aril.newsletter.sendmail.MailObject;
import com.aril.newsletter.utils.Util;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MailLogService implements IMailLogService {

    private final IMailLogRepository mailLogRepository;
    private final MailTemplateService templateService;
    private final MailGroupService groupService;
    private final ByJavaMailSender mailSender;

    public MailLogService(IMailLogRepository mailLogRepository, MailTemplateService templateService, MailGroupService groupService, ByJavaMailSender mailSender) {
        this.mailLogRepository = mailLogRepository;
        this.templateService = templateService;
        this.groupService = groupService;
        this.mailSender = mailSender;
    }

    @Override
    public String saveMailRequest(SendMailRequest sendMailRequest) {
        String requestCode = UUID.randomUUID().toString();
        MailGroupResponse mailGroupResponse = groupService.findById(sendMailRequest.getMailGroupId());
        MailTemplateResponse mailTemplateResponse = templateService.findById(sendMailRequest.getTemplateId());
        mailGroupResponse.getMailList().parallelStream().forEach(mailAddress -> {
            MailLog mailLog = new MailLog();
            mailLog.setMailTemplateId(mailTemplateResponse.getId());
            mailLog.setMailGroupId(mailGroupResponse.getId());
            mailLog.setMailFrom("yeness.drsn@gmail.com");
            mailLog.setMailTo(mailAddress.getMailAddress());
            mailLog.setParameters(Util.createParams(mailAddress));
            mailLog.setStatus(MailLogStatus.NEW);
            mailLog.setRequestCode(requestCode);
            mailLogRepository.save(mailLog);
        });
        new Thread(this::sendNewMails).start();
        return requestCode;
    }

    public void changeStatus(MailLog mailLog, MailLogStatus status) {
        mailLog.setStatus(status);
        mailLogRepository.save(mailLog);
    }

    @Override
    public List<MailLogResponse> findByRequestCode(String requestCode) {
        List<MailLog> mailLogs = mailLogRepository.findMailLogByRequestCode(requestCode);
        return mailLogs.stream()
                .map(mailLog -> new ModelMapper().map(mailLog, MailLogResponse.class))
                .collect(Collectors.toList());
    }


    public void sendNewMails() {
        List<MailLog> mailLogs = mailLogRepository.findMailLogByStatus(MailLogStatus.NEW);
        for (MailLog mailLog : mailLogs) {
            MailTemplateResponse template = templateService.findById(mailLog.getMailTemplateId());
            MailGroupResponse group = groupService.findById(mailLog.getMailGroupId());

            MailObject mailObject = new MailObject();
            mailObject.setTo(mailLog.getMailTo());
            mailObject.setSubject(template.getTitle());
            mailObject.setContent(Util.insertParams(template.getContent(), new Gson().fromJson(mailLog.getParameters(), new TypeToken<Map<String, Object>>() {
            }.getType())));
            List<String> collect = template.getAttachments().stream().map(MailAttachmentResponse::getName).collect(Collectors.toList());
            mailObject.setAttachments(collect);

            try {
                mailSender.sendMail(mailObject);
                changeStatus(mailLog, MailLogStatus.SUCCESS);
            } catch (Exception e) {
                changeStatus(mailLog, MailLogStatus.FAIL);
            }

        }
    }
}
