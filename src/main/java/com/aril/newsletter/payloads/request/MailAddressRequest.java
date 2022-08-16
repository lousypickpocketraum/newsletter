package com.aril.newsletter.payloads.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailAddressRequest {
    private String name;
    private String edas;
    private String mailAddress;
}
