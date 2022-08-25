package com.aril.newsletter.services;

import com.aril.newsletter.payloads.request.MailTemplateRequest;
import com.aril.newsletter.payloads.response.MailTemplateResponse;
import com.aril.newsletter.entities.MailAttachment;
import com.aril.newsletter.entities.MailTemplate;
import com.aril.newsletter.exceptions.NoSuchMailTemplateExistsException;
import com.aril.newsletter.repositories.IMailAttachmentRepository;
import com.aril.newsletter.repositories.IMailTemplateRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MailTemplateService {

    private final IMailTemplateRepository mailTemplateRepository;
    private final IMailAttachmentRepository mailAttachmentRepository;

    public MailTemplateService(IMailTemplateRepository mailTemplateRepository, IMailAttachmentRepository mailAttachmentRepository) {
        this.mailTemplateRepository = mailTemplateRepository;
        this.mailAttachmentRepository = mailAttachmentRepository;
    }

    public List<MailTemplateResponse> findAll() {
        List<MailTemplate> mailTemplate = mailTemplateRepository.findAll();
        return mailTemplate.stream()
                .map(mailTemplate1 -> new ModelMapper().map(mailTemplate1, MailTemplateResponse.class))
                .collect(Collectors.toList());
    }

    public List<MailTemplateResponse> findByActiveIs() {
        Optional<List<MailTemplate>> mailTemplate = mailTemplateRepository.findByActiveIs();
        return mailTemplate.get().stream()
                .map(mailTemplate1 -> new ModelMapper().map(mailTemplate1, MailTemplateResponse.class))
                .collect(Collectors.toList());
    }

    public MailTemplateResponse findById(Long id) {
        MailTemplate mailTemplate = mailTemplateRepository.findById(id).orElseThrow(NoSuchMailTemplateExistsException::new);
        return new ModelMapper().map(mailTemplate, MailTemplateResponse.class);
    }

    public MailTemplate save(MailTemplateRequest mailTemplateRequest) {
        MailTemplate mailTemplate = new ModelMapper().map(mailTemplateRequest, MailTemplate.class);
        mailTemplate.setCreateTime(LocalDateTime.now());
        mailTemplate.setActive(true);
        for (String attachment : mailTemplateRequest.getAttachments()) {
            MailAttachment mailAttachment = new MailAttachment();
            mailAttachment.setName(attachment);
            mailTemplate.getAttachments().add(mailAttachment);
        }
        return mailTemplateRepository.save(mailTemplate);
    }

    public MailTemplate update(Long id, MailTemplateRequest mailTemplateRequest) {
        Optional<MailTemplate> mailTemplate = mailTemplateRepository.findById(id);
        if (mailTemplate.isPresent()) {
            mailTemplate.get().setName(mailTemplateRequest.getName());
            mailTemplate.get().setTitle(mailTemplateRequest.getTitle());
            mailTemplate.get().setContent(mailTemplateRequest.getContent());
            mailTemplate.get().setUpdateTime(LocalDateTime.now());
            mailTemplate.get().getAttachments().clear();
            for (String attachment : mailTemplateRequest.getAttachments()) {
                Optional<MailAttachment> attachment_ = mailAttachmentRepository.findByName(attachment);
                MailAttachment mailAttachment = attachment_.orElseGet(MailAttachment::new);
                mailAttachment.setName(attachment);
                mailTemplate.get().getAttachments().add(mailAttachment);
            }
            return mailTemplateRepository.save(mailTemplate.get());
        }
        return null;
    }

    public void delete(Long id) {
        Optional<MailTemplate> mailTemplate = mailTemplateRepository.findById(id);
        if (mailTemplate.isPresent()) {
            mailTemplate.get().setDeleted(true);
            mailTemplate.get().setActive(false);
            mailTemplateRepository.save(mailTemplate.get());
        }
    }
}
