package com.aril.newsletter.payloads.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MailTemplateResponse {
    private Long id;
    private String name;
    private String title;
    private String content;
    private List<MailAttachmentResponse> attachments;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private boolean isActive;
}
