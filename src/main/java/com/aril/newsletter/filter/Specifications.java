package com.aril.newsletter.filter;

import com.aril.newsletter.constants.MailLogStatus;
import com.aril.newsletter.entities.MailLog;
import org.springframework.data.jpa.domain.Specification;

public class Specifications {
    public static Specification<MailLog> specMailGroupId(Long mailGroupId) {
        return (mailLog, cq, cb) -> cb.equal(mailLog.get("mailGroupId"), mailGroupId);
    }

    public static Specification<MailLog> specMailTemplateId(Long mailTemplateId) {
        return (mailLog, cq, cb) -> cb.equal(mailLog.get("mailTemplateId"), mailTemplateId);
    }

    public static Specification<MailLog> specStatus(MailLogStatus status) {
        return (mailLog, cq, cb) -> cb.equal(mailLog.get("status"), status);
    }
}
