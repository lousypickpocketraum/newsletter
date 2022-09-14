package com.aril.newsletter.services;

import com.aril.newsletter.entities.MailLog;
import com.aril.newsletter.payloads.request.MailLogRequest;
import com.aril.newsletter.payloads.request.SendMailRequest;
import com.aril.newsletter.payloads.response.MailLogResponse;

import java.util.List;

public interface IMailLogService {

    String saveMailRequest(SendMailRequest sendMailRequest);

    List<MailLogResponse> findByRequestCode(String requestCode);

    List<MailLogResponse> findByMailLogRequest(MailLogRequest mailLogRequest);


}
