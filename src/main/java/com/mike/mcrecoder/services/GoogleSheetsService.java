package com.mike.mcrecoder.services;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.*;
import com.mike.mcrecoder.constant.A1ExpressionConst;
import com.mike.mcrecoder.constant.ReturnCodeEnum;
import com.mike.mcrecoder.exception.UserNotFoundException;
import com.mike.mcrecoder.model.SpreadSheetDto;
import com.mike.mcrecoder.utils.A1ExpressionCreatUtil;
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

        try {
            Sheet sheet = getSheetsByTitle(userName);

            ValueRange sheetRange = sheets.spreadsheets().values().get(SPREADSHEET_ID, sheet.getProperties().getTitle()).execute();
            int lastColumnNum = sheetRange.getValues().size();

            String columnRange = A1ExpressionCreatUtil.getColumnRange(
                    lastColumnNum, sheet.getProperties().getTitle(),
                    A1ExpressionConst.COLUMN_YEAR, A1ExpressionConst.COLUMN_UPDATE_TIME
            );

            log.info("RealColumnSize: {}", lastColumnNum);

            if (lastColumnNum < 2)
                return ReturnCodeEnum.NOT_FOUND_RECORD;

//            List<String> rangeList = A1ExpressionCreatUtil.getColumnsRanges(columns);
//            BatchGetValuesResponse response = sheets.spreadsheets().values().batchGet(SPREADSHEET_ID).setRanges(rangeList).execute();
//            List<ValueRange> valueRangeList = response.getValueRanges();
//            log.info("valueRangeList size: {}", valueRangeList.size());
//
//            SpreadSheetDto spreadSheetDto = SpreadSheetDto.from(valueRangeList);
//            valueRangeList.forEach(valueRange -> {
//                log.info("Range: {} / Value: {}", valueRange.getRange(), valueRange.getValues().get(0).get(0));
//            });
//            log.info(spreadSheetDto.toString());
//            System.err.println(response.values());


            ValueRange valueRange = sheets.spreadsheets().values().get(SPREADSHEET_ID, columnRange).execute();
            log.info("Range Is: {}", valueRange.getRange());
            valueRange.getValues().forEach(eachColumnValue ->
                    eachColumnValue.forEach(eachRowValue ->
                            log.info("eachRowValue: {}", eachRowValue)
                    ));

        } catch (UserNotFoundException userNotFoundException) {
            return ReturnCodeEnum.NOT_FOUND_USER;
        }
        return ReturnCodeEnum.QUERY_PREVIOUS;

    }

}
