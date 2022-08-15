package com.aril.newsletter.sendmail;

import com.aril.newsletter.payloads.response.MailAttachmentResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MailObject {
    private String to;
    private String content;
    private String subject;
    private List<String> attachments;
}
