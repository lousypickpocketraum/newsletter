package com.aril.newsletter.payloads.request;

import com.aril.newsletter.constants.MailLogStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailLogRequest {
    private Long mailGroupId;
    private Long mailTemplateId;
    private MailLogStatus status;
}
