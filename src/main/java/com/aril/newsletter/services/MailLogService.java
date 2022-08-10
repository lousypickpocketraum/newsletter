package com.aril.newsletter.services;

import com.aril.newsletter.constants.MailLogStatus;
import com.aril.newsletter.entities.MailGroup;
import com.aril.newsletter.entities.MailLog;
import com.aril.newsletter.exceptions.NoSuchMailGroupExistsException;
import com.aril.newsletter.payloads.response.MailGroupResponse;
import com.aril.newsletter.payloads.response.MailLogResponse;
import com.aril.newsletter.repositories.IMailLogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MailLogService implements IMailLogService {
    private final IMailLogRepository mailLogRepository;

    public MailLogService(IMailLogRepository mailLogRepository) {
        this.mailLogRepository = mailLogRepository;
    }

    @Override
    public MailLogResponse findById(Long id) {
        Optional <MailLog> mailLog = mailLogRepository.findById(id);
        MailLogResponse mailLogResponse = new ModelMapper().map(mailLog.get(), MailLogResponse.class);
        return mailLogResponse;
    }

    @Override
    public List<MailLogResponse> findByUUID(String requestCode) {
        List<MailLog> mailLogs = mailLogRepository.findMailLogByRequestCode(requestCode);
        List<MailLogResponse> mailLogResponse = mailLogs.stream()
                .map(mailLog -> new ModelMapper().map(mailLog, MailLogResponse.class))
                .collect(Collectors.toList());
        return mailLogResponse;
    }

    @Override
    public List<MailLogResponse> findAll() {
        List<MailLog> mailLogs = mailLogRepository.findAll();
        List<MailLogResponse> mailLogResponses = mailLogs.stream()
                .map(mailLog -> new ModelMapper().map(mailLog, MailLogResponse.class))
                .collect(Collectors.toList());
        return mailLogResponses;
    }

    @Override
    public void save(MailLogResponse mailLogResponse) {
        MailLog mailLog = new ModelMapper().map(mailLogResponse, MailLog.class);
        mailLogRepository.save(mailLog);
    }
}
