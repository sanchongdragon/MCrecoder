package com.mike.mcrecoder.utils;

import com.mike.mcrecoder.constant.A1ExpressionConst;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class A1ExpressionCreatUtil {

    private static final String[] COLUMNS = {
            A1ExpressionConst.COLUMN_YEAR, A1ExpressionConst.COLUMN_MONTH, A1ExpressionConst.COLUMN_START_TIME,
            A1ExpressionConst.COLUMN_END_TIME, A1ExpressionConst.COLUMN_CREATE_TIME, A1ExpressionConst.COLUMN_UPDATE_TIME
    };

    private static final String COLUMN_RANGE_TEMPLATE = "{0}!{1}{2}:{3}{4}";
    public static String create(String... elements){
        return StringUtils.join(elements);
    }

    public static String getColumnRange(int columnNum, String sheetName, String startRow, String endRow){
        return MessageFormat.format(COLUMN_RANGE_TEMPLATE, sheetName, startRow, columnNum, endRow, columnNum);
    }

    /**
     * 取得指定 columnNumber 的 rangeList
     * @param columnNum 行數
     * @return 指定的 RangeList
     */
    public static List<String> getColumnsRanges(int columnNum){
        return Arrays.stream(COLUMNS).map(column -> column + columnNum).collect(Collectors.toList());
    }

}
