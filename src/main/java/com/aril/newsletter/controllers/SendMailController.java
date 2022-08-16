package com.aril.newsletter.controllers;

import com.aril.newsletter.payloads.request.SendMailRequest;
import com.aril.newsletter.payloads.response.MailLogResponse;
import com.aril.newsletter.services.MailLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
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
