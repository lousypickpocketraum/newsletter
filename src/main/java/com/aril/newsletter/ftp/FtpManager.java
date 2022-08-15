package com.aril.newsletter.ftp;

import org.springframework.core.io.ClassPathResource;

public class FtpManager {

    // config

    public static ClassPathResource getFile(String fileName){
        return new ClassPathResource(fileName); // todo
    }
}
