package com.aril.newsletter.entities;

import com.aril.newsletter.constants.MailLogStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class MailLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long mailTemplateId;
    private Long mailGroupId;
    @Enumerated(EnumType.STRING)
    private MailLogStatus status;
    @CreationTimestamp
    private LocalDateTime dateTime;
    private String parameters;
    private String mailFrom;
    private String mailTo;
    private String cc;
    private String bcc;
    private String requestCode;
}
