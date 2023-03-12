package com.mike.mcrecoder.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

    private static final String PATTERN1 = "yyyy-MM-dd";
    private static final String PATTERN2 = "yyyy-MM-dd HH:mm:ss";
    public static LocalDate parseDate(String value){
        return LocalDate.parse(value, DateTimeFormatter.ofPattern(PATTERN1));
    }

    public static LocalDateTime parseDateTime(String value){
        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(PATTERN2));
    }

    public static String formatDate(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern(PATTERN1));
    }

    public static String formatDateTime(LocalDateTime dateTime){
        return dateTime.format(DateTimeFormatter.ofPattern(PATTERN2));
    }
}
