package com.mike.mcrecoder.utils;

import com.mike.mcrecoder.constant.A1ExpressionConst;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.buf.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class A1ExpressionCreatUtil {

    private static final String[] columns = {
            A1ExpressionConst.COLUMN_YEAR, A1ExpressionConst.COLUMN_MONTH, A1ExpressionConst.COLUMN_START_TIME,
            A1ExpressionConst.COLUMN_END_TIME, A1ExpressionConst.COLUMN_CREATE_TIME, A1ExpressionConst.COLUMN_UPDATE_TIME
    };
    public static String create(String... elements){
        return StringUtils.join(elements);
    }

    /**
     * 取得指定 columnNumber 的 rangeList
     * @param columnNum 行數
     * @return 指定的 RangeList
     */
    public static List<String> getColumnsRanges(int columnNum){
        return Arrays.stream(columns).map(column -> column + columnNum).collect(Collectors.toList());
    }

}
