package com.aril.newsletter.repositories;

import com.aril.newsletter.entities.MailAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IMailAttachmentRepository extends JpaRepository<MailAttachment, Long> {
    Optional<MailAttachment> findByName(String name);
}
