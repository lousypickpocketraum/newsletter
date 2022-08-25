package com.aril.newsletter.payloads.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MailTemplateRequest {
    private String name;
    private String title;
    private String content;
    private List<String> attachments;
}
