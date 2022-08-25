package com.aril.newsletter.repositories;

import com.aril.newsletter.entities.MailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMailTemplateRepository extends JpaRepository<MailTemplate, Long> {
    Optional<MailTemplate> findByName(String name);
    @Query(value = "SELECT * from mail_template where is_active=1", nativeQuery = true)
    Optional<List<MailTemplate>> findByActiveIs();
}
