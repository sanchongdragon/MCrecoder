package com.mike.mcrecoder.configure;

import com.google.api.services.sheets.v4.Sheets;
import com.mike.mcrecoder.services.GoogleSheetsServiceUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Configuration
public class GoogleSheetConfigure {
    @Bean
    public Sheets createSheets() throws GeneralSecurityException, IOException {
        // use Util to get Sheets API
        return GoogleSheetsServiceUtil.getSheets();
    }

}
