package com.aril.newsletter.payloads.response;

import com.aril.newsletter.constants.MailLogStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MailLogResponse {
    private Long id;
    private Long mailTemplateId;
    private Long mailGroupId;
    private MailLogStatus status;
    private LocalDateTime dateTime;
    private String parameters;
    private String mailFrom;
    private String mailTo;
    private String cc;
    private String bcc;
    private String requestCode;
}
