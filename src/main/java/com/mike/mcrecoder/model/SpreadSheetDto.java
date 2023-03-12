package com.mike.mcrecoder.model;

import com.google.api.services.sheets.v4.model.ValueRange;
import com.mike.mcrecoder.constant.A1ExpressionConst;
import com.mike.mcrecoder.utils.DateUtil;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Data
@Builder
public class SpreadSheetDto {

    private int year;
    private int month;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    public static SpreadSheetDto from(List<ValueRange> valueRanges){
        SpreadSheetDto spreadSheetDto = SpreadSheetDto.builder().build();
        valueRanges.forEach(valueRange -> {
            String range = valueRange.getRange();
            if(StringUtils.contains(range, A1ExpressionConst.COLUMN_YEAR))
                spreadSheetDto.setYear(Integer.parseInt(Objects.requireNonNull(getValue(valueRange.getValues()))));
            else if(StringUtils.contains(range, A1ExpressionConst.COLUMN_MONTH))
                spreadSheetDto.setMonth(Integer.parseInt(Objects.requireNonNull(getValue(valueRange.getValues()))));
            else if(StringUtils.contains(range, A1ExpressionConst.COLUMN_START_TIME))
                spreadSheetDto.setStartDate(DateUtil.parseDate(getValue(valueRange.getValues())));
            else if(StringUtils.contains(range, A1ExpressionConst.COLUMN_END_TIME))
                spreadSheetDto.setEndDate(DateUtil.parseDate(getValue(valueRange.getValues())));
            else if(StringUtils.contains(range, A1ExpressionConst.COLUMN_CREATE_TIME))
                spreadSheetDto.setCreateDateTime(DateUtil.parseDateTime(getValue(valueRange.getValues())));
            else if(StringUtils.contains(range, A1ExpressionConst.COLUMN_UPDATE_TIME))
                spreadSheetDto.setUpdateDateTime(DateUtil.parseDateTime(getValue(valueRange.getValues())));
        });
        return spreadSheetDto;
    }

    private static String getValue(List<List<Object>> value){
        if(CollectionUtils.isEmpty(value)) return null;
        if(CollectionUtils.isEmpty(value.get(0))) return null;
        return (String) value.get(0).get(0);
    }

}
