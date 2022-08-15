package com.aril.newsletter.services;

import com.aril.newsletter.constants.MailLogStatus;
import com.aril.newsletter.payloads.request.SendMailRequest;
import com.aril.newsletter.payloads.response.MailLogResponse;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IMailLogService {

    String saveMailRequest(SendMailRequest sendMailRequest);

    List<MailLogResponse> findByRequestCode(String requestCode);

}
