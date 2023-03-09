package com.mike.mcrecoder.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReturnCodeEnum {

    // not found
    NOT_FOUND_USER("001", "找不到使用者"),
    NOT_FOUND_RECORD("002", "找不到任何紀錄"),

    // query
    QUERY_PREVIOUS("200", "前一次日期是 {0} 到 {1}"),
    QUERY_NEXT("201", "下一次日期是 {0}"),
    QUERY_CYCLE("202", "總共紀錄了 {0} 次, 平均週期是 {1} 天"),

    // success
    SUCCESS_SAVE("301", "紀錄成功!"),

    // fail
    FAIL_SAVE("401", "紀錄失敗!"),

    // error
    ERR_SYSTEM("999", "系統錯誤");

    private final String code;
    private final String message;
}
