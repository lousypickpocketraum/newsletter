package com.aril.newsletter.services;

import java.util.Map;

public interface IAsyncSendMailService {
    void asyncSendMail(String mailAddress, Long mailTemplateId, Map<String, Object> model, Long mailLogId);
}
