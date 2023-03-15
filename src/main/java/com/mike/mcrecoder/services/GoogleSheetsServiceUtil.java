package com.mike.mcrecoder.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.mike.mcrecoder.utils.GoogleAuthorizeUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GoogleSheetsServiceUtil {

    private static final String APPLICATION_NAME = "mcrecoder";

    public static Sheets getSheets() throws GeneralSecurityException, IOException {

        // get service account GoogleCredential
        GoogleCredential googleCredential = GoogleAuthorizeUtil.getServiceCredential();

        // Put the GoogleCredential to Use "Sheets" API
        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), googleCredential
        ).setApplicationName(APPLICATION_NAME).build();

    }

}
