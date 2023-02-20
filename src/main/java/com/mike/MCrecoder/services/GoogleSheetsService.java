package com.mike.MCrecoder.services;

import com.google.api.client.auth.oauth2.Credential;
import com.mike.MCrecoder.utils.GoogleAuthorizeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
@Slf4j
public class GoogleSheetsService {

    public static void getSheet() throws GeneralSecurityException, IOException {

        Credential credential = GoogleAuthorizeUtil.getCredential();



    }

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        GoogleSheetsService.getSheet();
    }

}
