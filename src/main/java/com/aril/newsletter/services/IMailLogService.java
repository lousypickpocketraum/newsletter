package com.aril.newsletter.services;

import com.aril.newsletter.payloads.response.MailLogResponse;

import java.util.List;
import java.util.UUID;

public interface IMailLogService {

    MailLogResponse findById(Long id);

    List<MailLogResponse> findByUUID(String requestCode);

    List<MailLogResponse> findAll();

    void save(MailLogResponse mailLogResponse);

}
