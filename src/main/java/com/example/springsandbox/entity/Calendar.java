package com.example.springsandbox.entity;

import lombok.Data;

import java.time.LocalDate;

/**
 * カレンダー Entityクラス
 */
@Data
public class Calendar {
    /**
     * カレンダーID
     */
    private Integer id;
    /**
     * 日付
     */
    private LocalDate date;
    /**
     * 祝日名
     */
    private String holidayName;
}
