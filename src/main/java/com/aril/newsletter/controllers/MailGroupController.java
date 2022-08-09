package com.aril.newsletter.controllers;

import com.aril.newsletter.payloads.request.MailGroupRequest;
import com.aril.newsletter.payloads.response.MailGroupResponse;
import com.aril.newsletter.entities.MailGroup;
import com.aril.newsletter.services.MailGroupService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MailGroupController {
    @Autowired
    private MailGroupService service;

    @GetMapping("/mailGroups")
    public ResponseEntity<List<MailGroupResponse>> getAllMailGroups(){
        List<MailGroupResponse> mailGroupResponses = service.findAll();
        return new ResponseEntity<>(mailGroupResponses, HttpStatus.OK);
    }
    @GetMapping("/mailGroups/{id}")
    public ResponseEntity<MailGroupResponse> getMailGroupById(@PathVariable Long id){
        MailGroupResponse mailGroupResponse = service.findById(id);
        return new ResponseEntity<>(mailGroupResponse, HttpStatus.OK);
    }
    @PostMapping("/mailGroups")
    public ResponseEntity<MailGroupResponse> save(@RequestBody MailGroupRequest mailGroupRequest){
        MailGroup mailGroup = service.save(mailGroupRequest);
        MailGroupResponse response = new ModelMapper().map(mailGroup, MailGroupResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PutMapping("/mailGroups/{id}")
    public ResponseEntity<MailGroupResponse> update(@PathVariable Long id, @RequestBody MailGroupRequest mailGroupRequest){
        MailGroup mailGroup = service.update(id,mailGroupRequest);
        MailGroupResponse response = new ModelMapper().map(mailGroup, MailGroupResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @DeleteMapping("/mailGroups/{id}")
    public ResponseEntity<MailGroupResponse> delete(@PathVariable Long id){
        MailGroup mailGroup = service.delete(id);
        MailGroupResponse response = new ModelMapper().map(mailGroup, MailGroupResponse.class);
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
