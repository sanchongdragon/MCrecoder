package com.mike.mcrecoder.services;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import com.mike.mcrecoder.constant.ReturnCodeEnum;
import com.mike.mcrecoder.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GoogleSheetsService {

    // create a google spreadsheet and find that id
    private static final String SPREADSHEET_ID = "1yqqzEIELCAYdjiHs4g2nYqxWfaXBJVJFeZLJfi6GDig";
    @Autowired
    private Sheets sheets;

    public Sheet getSheetsByTitle(String userName) throws IOException, UserNotFoundException {
        // filter sheet by userName
        List<Sheet> allSheets = getAllSheets();
        return allSheets.stream().filter(
                sheet1 -> userName.equals(sheet1.getProperties().getTitle())
        ).findFirst().orElseThrow(() -> new UserNotFoundException(userName));
    }

    public List<Sheet> getAllSheets() throws IOException {
        // get all sheets
        Spreadsheet allSheets = sheets.spreadsheets().get(SPREADSHEET_ID).execute();
//        sheets.spreadsheets().values().batchGet()
        return allSheets.getSheets();
    }

    public ReturnCodeEnum queryPrevious(String userName) throws IOException {
        List<String> rangeList = new ArrayList<>();
        for (int i = 1; i <= 26; i++) {
            rangeList.add("A" + i);
        }

        try {
            Sheet sheet = getSheetsByTitle(userName);
            Integer columns = sheet.getProperties().getGridProperties().getColumnCount();

            if(columns == null || columns < 2)
                return ReturnCodeEnum.NOT_FOUND_RECORD;

        }catch (UserNotFoundException userNotFoundException){
            return ReturnCodeEnum.NOT_FOUND_USER;
        }
        BatchGetValuesResponse response = sheets.spreadsheets().values().batchGet(SPREADSHEET_ID).setRanges(rangeList).execute();
        System.err.println(response.values());
        return ReturnCodeEnum.QUERY_PREVIOUS;
    }

}
