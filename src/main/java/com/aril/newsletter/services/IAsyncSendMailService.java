package com.aril.newsletter.services;

import com.aril.newsletter.payloads.response.MailLogResponse;
import com.aril.newsletter.payloads.response.MailTemplateResponse;
import com.aril.newsletter.payloads.response.SendMailResponse;

import java.util.Map;

public interface IAsyncSendMailService {
    MailLogResponse sendMail(String mailAddress, Long mailTemplateId, Map<String, Object> model);
}
