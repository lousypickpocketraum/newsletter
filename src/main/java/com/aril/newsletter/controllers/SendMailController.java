package com.aril.newsletter.controllers;

import com.aril.newsletter.payloads.request.SendMailRequest;
import com.aril.newsletter.payloads.response.MailLogResponse;
import com.aril.newsletter.services.MailLogService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SendMailController {

    private final MailLogService mailLogService;

    public SendMailController(MailLogService mailLogService) {
        this.mailLogService = mailLogService;
    }

    @PostMapping("/sendingMail")
    public String sendMail(@RequestBody SendMailRequest sendMailRequest){
        return mailLogService.saveMailRequest(sendMailRequest);
    }

    @PostMapping("/trackMail/{requestCode}")
    public List<MailLogResponse> trackMail(@PathVariable String requestCode){
        return mailLogService.findByRequestCode(requestCode);
    }

}
