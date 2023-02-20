package com.mike.MCrecoder.services;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Sheet;
import com.mike.MCrecoder.utils.GoogleAuthorizeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
@Slf4j
public class GoogleSheetsServiceUtil {

    private static final String APPLICATION_NAME = "McRecorder";

    public static Sheets getSheets() throws GeneralSecurityException, IOException {

        Credential credential = GoogleAuthorizeUtil.getCredential();

        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), credential
        ).setApplicationName(APPLICATION_NAME).build();

    }

}
