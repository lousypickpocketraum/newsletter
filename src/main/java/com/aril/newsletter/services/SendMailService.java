package com.aril.newsletter.services;

import com.aril.newsletter.constants.MailLogStatus;
import com.aril.newsletter.payloads.request.SendMailRequest;
import com.aril.newsletter.entities.MailLog;
import com.aril.newsletter.payloads.response.MailGroupResponse;
import com.aril.newsletter.payloads.response.MailLogResponse;
import com.aril.newsletter.payloads.response.MailTemplateResponse;
import com.aril.newsletter.repositories.IMailLogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SendMailService {

    private final MailTemplateService templateService;
    private final MailGroupService groupService;
    private final AsyncSendMailService asyncSendMailService;
    private final IMailLogRepository mailLogRepository;

    public SendMailService(MailTemplateService templateService, MailGroupService groupService, AsyncSendMailService asyncSendMailService, IMailLogRepository mailLogRepository) {

        this.templateService = templateService;
        this.groupService = groupService;
        this.asyncSendMailService = asyncSendMailService;
        this.mailLogRepository = mailLogRepository;
    }

    public String saveMailRequest(SendMailRequest sendMailRequest, Map<String, Object> model) {
        String requestCode = UUID.randomUUID().toString();
        MailGroupResponse mailGroupResponse = groupService.findById(sendMailRequest.getMailGroupId());
        MailTemplateResponse mailTemplateResponse = templateService.findById(sendMailRequest.getTemplateId());
        mailGroupResponse.getMailList().parallelStream().forEach(mailAddress -> {
            MailLog mailLog = new MailLog();
            mailLog.setMailTemplateId(mailTemplateResponse.getId());
            mailLog.setMailGroupId(mailGroupResponse.getId());
            mailLog.setMailFrom("yeness.drsn@gmail.com");
            mailLog.setMailTo(mailAddress.getMail());
            mailLog.setParameters(model.toString());
            mailLog.setStatus(MailLogStatus.NEW);
            mailLog.setRequestCode(requestCode);
            mailLogRepository.save(mailLog);
        });
        sendMail(requestCode,model);
        return requestCode;
    }

    public void sendMail(String requestCode, Map<String, Object> model){
        List<MailLog> mailLogs = mailLogRepository.findMailLogByRequestCode(requestCode);
        List<MailLogResponse> mailLogResponses = mailLogs.stream()
                .map(mailLog1 -> new ModelMapper().map(mailLog1,MailLogResponse.class))
                .collect(Collectors.toList());
        mailLogResponses.stream().forEach(mailLog2 -> {
            asyncSendMailService.asyncSendMail(mailLog2.getMailTo(),mailLog2.getMailTemplateId(),model,mailLog2.getId());
        });
    }
}
