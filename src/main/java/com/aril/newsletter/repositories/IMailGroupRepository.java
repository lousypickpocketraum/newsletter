package com.aril.newsletter.repositories;

import com.aril.newsletter.entities.MailGroup;
import com.aril.newsletter.entities.MailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IMailGroupRepository extends JpaRepository<MailGroup, Long> {
    @Query(value = "SELECT * from mail_group where is_active=true ", nativeQuery = true)
    Optional<List<MailGroup>> findByActiveIs();
}
