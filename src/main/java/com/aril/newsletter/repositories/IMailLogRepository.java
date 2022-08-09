package com.aril.newsletter.repositories;

import com.aril.newsletter.entities.MailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMailLogRepository extends JpaRepository<MailLog,Long> {
}
