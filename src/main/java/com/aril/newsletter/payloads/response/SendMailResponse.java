package com.aril.newsletter.payloads.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendMailResponse {
    private String message;
    private boolean status;
}
