package com.aril.newsletter.controllers;

import com.aril.newsletter.payloads.request.SendMailRequest;
import com.aril.newsletter.payloads.response.MailLogResponse;
import com.aril.newsletter.payloads.response.SendMailResponse;
import com.aril.newsletter.services.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class SendMailController {
    @Autowired
    private SendMailService sendMailService;

    @PostMapping("/sendingMail")
    public String sendMail(@RequestBody SendMailRequest sendMailRequest){
        Map<String,Object> model = new HashMap<>();
        model.put("name","Enes");
        model.put("location","İstanbul");
        return sendMailService.saveMailRequest(sendMailRequest,model);
    }

    @PostMapping("/trackMail/{requestCode}")
    public List<MailLogResponse> trackMail(@PathVariable UUID requestCode, Map<String, Object> model){
        return sendMailService.sendMail(model);
    }

}
