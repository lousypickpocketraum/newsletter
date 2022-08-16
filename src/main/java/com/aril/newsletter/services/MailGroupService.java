package com.aril.newsletter.services;

import com.aril.newsletter.payloads.request.MailAddressRequest;
import com.aril.newsletter.payloads.request.MailGroupRequest;
import com.aril.newsletter.payloads.response.MailAddressResponse;
import com.aril.newsletter.payloads.response.MailGroupResponse;
import com.aril.newsletter.entities.MailEntity;
import com.aril.newsletter.entities.MailGroup;
import com.aril.newsletter.exceptions.NoSuchMailGroupExistsException;
import com.aril.newsletter.repositories.IMailEntityRepository;
import com.aril.newsletter.repositories.IMailGroupRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MailGroupService implements IMailGroupService{
    private final IMailGroupRepository mailGroupRepository;
    private final IMailEntityRepository mailEntityRepository;

    public MailGroupService(IMailGroupRepository mailGroupRepository, IMailEntityRepository mailEntityRepository) {
        this.mailGroupRepository = mailGroupRepository;
        this.mailEntityRepository = mailEntityRepository;
    }

    public List<MailGroupResponse> findAll() {
        List<MailGroup> mailGroups = mailGroupRepository.findAll();
        List<MailGroupResponse> mailGroupResponses = mailGroups.stream()
                .map(mailGroup1 -> new ModelMapper().map(mailGroup1, MailGroupResponse.class))
                .collect(Collectors.toList());
        return mailGroupResponses;
    }

    public MailGroupResponse findById(Long id){
        MailGroup mailGroup = mailGroupRepository.findById(id).orElseThrow(NoSuchMailGroupExistsException::new);
        MailGroupResponse mailGroupResponse = new ModelMapper().map(mailGroup, MailGroupResponse.class);
        return mailGroupResponse;
    }

    public MailGroup save(MailGroupRequest mailGroupRequest) {
        MailGroup mailGroup = new ModelMapper().map(mailGroupRequest, MailGroup.class);
        mailGroup.setCreateTime(LocalDateTime.now());
        mailGroup.setActive(true);
        for(MailAddressRequest mail : mailGroupRequest.getMailList()) {
            Optional<MailEntity> mail_ = mailEntityRepository.findByMailAddress(mail.getMailAddress());
            MailEntity mailEntity = mail_.orElseGet(MailEntity::new);
            mailEntity.setMailAddress(mail.getMailAddress());
            mailEntity.setName(mail.getName());
            mailEntity.setEdas(mail.getEdas());
            mailGroup.getMailEntities().add(mailEntity);
        }
        return mailGroupRepository.save(mailGroup);
    }
    public MailGroup update(Long id, MailGroupRequest mailGroupRequest){
        Optional<MailGroup> mailGroup = mailGroupRepository.findById(id);
        if(mailGroup.isPresent()){
            mailGroup.get().setName(mailGroupRequest.getName());
            mailGroup.get().setTag(mailGroupRequest.getTag());
            mailGroup.get().getMailEntities().clear();
            for(MailAddressRequest mail : mailGroupRequest.getMailList()) {
                Optional<MailEntity> mail_ = mailEntityRepository.findByMailAddress(mail.getMailAddress());
                MailEntity mailEntity = mail_.orElseGet(MailEntity::new);
                mailEntity.setMailAddress(mail.getMailAddress());
                mailGroup.get().getMailEntities().add(mailEntity);
            }
            return mailGroupRepository.save(mailGroup.get());
        }
        return null;
    }
    public MailGroup delete(Long id){
        Optional<MailGroup> mailGroup = mailGroupRepository.findById(id);
        if (mailGroup.isPresent()){
            mailGroup.get().setActive(false);
            mailGroup.get().setDeleted(true);
            return mailGroupRepository.save(mailGroup.get());
        }
        return null;
    }
}
