package com.mike.MCrecoder.services;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GoogleSheetsService {

    // create a google spreadsheet and find that id
    private static final String SPREADSHEET_ID = "1yqqzEIELCAYdjiHs4g2nYqxWfaXBJVJFeZLJfi6GDig";

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        // use Util to get Sheets API
        final Sheets sheets = GoogleSheetsServiceUtil.getSheets();

        List<String> rangeList = new ArrayList<>();
        for(int i = 1; i <= 26 ; i++){
            rangeList.add("A" + i);
        }

        log.info("Access Google Sheets ApplicationName: {}", sheets.getApplicationName());

        BatchGetValuesResponse response = sheets.spreadsheets().values().batchGet(SPREADSHEET_ID).setRanges(rangeList).execute();
        System.err.println(response.values());
        sheets.spreadsheets().values().batchGet(SPREADSHEET_ID).

        System.err.println(sheets);

    }

}
