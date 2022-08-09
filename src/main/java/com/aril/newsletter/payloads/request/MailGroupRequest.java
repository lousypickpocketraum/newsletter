package com.aril.newsletter.payloads.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MailGroupRequest {
    private String name;
    private String tag;
    private List<String> mailList;

}
