package com.aril.newsletter.controllers;

import com.aril.newsletter.payloads.request.MailTemplateRequest;
import com.aril.newsletter.payloads.response.MailTemplateResponse;
import com.aril.newsletter.entities.MailTemplate;
import com.aril.newsletter.services.MailTemplateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
public class MailTemplateController {
    @Autowired
    MailTemplateService service;

    @GetMapping("/mailTemplates")
    public ResponseEntity<List<MailTemplateResponse>> getAllMailTemplates(){
        List<MailTemplateResponse> mailTemplateResponses = service.findAll();
        return new ResponseEntity<>(mailTemplateResponses, HttpStatus.OK);
    }

    @GetMapping("/mailTemplates/{id}")
    public ResponseEntity<MailTemplateResponse> getMailTemplate(@PathVariable Long id){
        MailTemplateResponse mailTemplateResponse = service.findById(id);
        return new ResponseEntity<>(mailTemplateResponse, HttpStatus.OK);
    }

    @PostMapping("/mailTemplates")
    public ResponseEntity<MailTemplateResponse> save(@RequestBody MailTemplateRequest mailTemplateRequest){
        MailTemplate mailTemplate = service.save(mailTemplateRequest);
        MailTemplateResponse response = new ModelMapper().map(mailTemplate, MailTemplateResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/mailTemplate/{id}")
    public ResponseEntity<MailTemplateResponse> update(@PathVariable("id") Long id, @RequestBody MailTemplateRequest mailTemplateRequest){
        MailTemplate mailTemplate = service.update(id, mailTemplateRequest);
        MailTemplateResponse response = new ModelMapper().map(mailTemplate, MailTemplateResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/mailTemplate/{id}")
    public ResponseEntity<MailTemplateResponse> delete(@PathVariable("id") Long id){
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
