package com.mike.MCrecoder.utils;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.List;

public class GoogleAuthorizeUtil {

    private GoogleAuthorizeUtil(){}

    /**
     * 使用 google service account 對 google 做 server 對 server 的呼叫
     * @return {@link GoogleCredential}
     * @throws IOException not found service account.json File will throw
     */
    public static GoogleCredential getServiceCredential() throws IOException {

//         add SpreadSheets Scope
        List<String> scopes = List.of(SheetsScopes.SPREADSHEETS);
//         load your google service account credential
//         this credential will generate by google
//         more info : https://developers.google.com/identity/protocols/oauth2/service-account?hl=zh-tw
        GoogleCredential googleCredential = GoogleCredential.fromStream(
                new ClassPathResource("static/service-account-credential.json").getInputStream()
        );

        // create credential will access OAuth Scopes
        googleCredential = googleCredential.createScoped(scopes);
        return googleCredential;
    }
}
