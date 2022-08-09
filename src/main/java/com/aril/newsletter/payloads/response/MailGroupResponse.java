package com.aril.newsletter.payloads.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class MailGroupResponse {
    private Long id;
    private String name;
    private String tag;
    private List<MailAddressResponse> mailList;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private boolean isActive;

}
