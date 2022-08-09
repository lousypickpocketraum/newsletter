package com.aril.newsletter.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String mailAddress;
    private String name;

}
