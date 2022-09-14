package com.aril.newsletter.repositories;

import com.aril.newsletter.constants.MailLogStatus;
import com.aril.newsletter.entities.MailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMailLogRepository extends JpaRepository<MailLog,Long>, JpaSpecificationExecutor<MailLog> {
    List<MailLog> findMailLogByRequestCode(String requestCode);

    List<MailLog> findMailLogByStatus(MailLogStatus status);

}
