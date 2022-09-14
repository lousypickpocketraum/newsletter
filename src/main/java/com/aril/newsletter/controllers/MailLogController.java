package com.aril.newsletter.controllers;

import com.aril.newsletter.payloads.request.MailLogRequest;
import com.aril.newsletter.payloads.response.MailLogResponse;
import com.aril.newsletter.services.MailLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class MailLogController {

    private final MailLogService mailLogService;

    public MailLogController(MailLogService mailLogService) {
        this.mailLogService = mailLogService;
    }

    @PostMapping("/mailLogs")
    public List<MailLogResponse> findByMailLogRequest(@RequestBody(required = false) MailLogRequest mailLogRequest){
        return mailLogService.findByMailLogRequest(mailLogRequest);
    }
}
