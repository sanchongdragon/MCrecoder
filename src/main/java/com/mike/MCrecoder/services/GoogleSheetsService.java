package com.mike.MCrecoder.services;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class GoogleSheetsService {

    // create a google spreadsheet and find that id
    private static final String SPREADSHEET_ID = "1yqqzEIELCAYdjiHs4g2nYqxWfaXBJVJFeZLJfi6GDig";
    @Autowired
    private Sheets sheets;

    public void getAllSheets() throws IOException {
        Sheets.Spreadsheets.Get spreadSheet = sheets.spreadsheets().get(SPREADSHEET_ID);
        log.info("SpreadSheetsId: {} / Size: {}", spreadSheet.getSpreadsheetId(), spreadSheet.size());
        Sheets.Spreadsheets.SheetsOperations sheetsOperations = sheets.spreadsheets().sheets();
        Spreadsheet allSheets = spreadSheet.execute();
        List<Sheet> listOfSheet = allSheets.getSheets().stream().filter(
                sheet -> {
                    log.info("Sheet Title: {}", sheet.getProperties().getTitle());
                    return true;
                }
        ).collect(Collectors.toList());
        log.info("JASMINE Sheet Size: {}", listOfSheet.size());
    }

    public void queryPrevious() throws IOException {
        log.info("Access Google Sheets ApplicationName: {}", sheets.getApplicationName());
        List<String> rangeList = new ArrayList<>();
        for(int i = 1; i <= 26 ; i++){
            rangeList.add("A" + i);
        }
//        System.err.println(sheets.spreadsheets().get(SPREADSHEET_ID).getRanges().toArray());
        BatchGetValuesResponse response = sheets.spreadsheets().values().batchGet(SPREADSHEET_ID).setRanges(rangeList).execute();
        System.err.println(response.values());
    }

}
