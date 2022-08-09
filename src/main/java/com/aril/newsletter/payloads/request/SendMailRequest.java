package com.aril.newsletter.payloads.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class SendMailRequest {
    @NotNull(message = "templade id boş olamaz")
    private Long templateId;
    @NotNull(message = "mail group id boş olamaz")
    private Long mailGroupId;
}
