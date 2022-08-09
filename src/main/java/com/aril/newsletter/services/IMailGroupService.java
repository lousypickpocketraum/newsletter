package com.aril.newsletter.services;

import com.aril.newsletter.payloads.response.MailGroupResponse;

import java.util.List;

public interface IMailGroupService {
    MailGroupResponse findById(Long id);

    List<MailGroupResponse> findAll();
}
