package com.aril.newsletter.payloads.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MailAddressResponse {
    private Long id;
    private String mailAddress;
    private String name;
    private String edas;
}
