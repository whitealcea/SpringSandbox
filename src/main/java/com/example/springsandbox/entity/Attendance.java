package com.example.springsandbox.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 勤怠 Entityクラス
 */
@Data
public class Attendance {
    /**
     * 勤怠ID
     */
    private Integer id;
    /**
     * 従業員ID
     */
    private Integer employeeId;
    /**
     * 日付
     */
    private LocalDate date;
    /**
     * 出勤時間
     */
    private LocalTime startTime;
    /**
     * 退勤時間
     */
    private LocalTime endTime;
    /**
     * 休憩時間
     */
    private LocalTime breakTime;
    /**
     * 遅刻時間
     */
    private LocalTime lateTime;
    /**
     * 早退時間
     */
    private LocalTime leaveEarlyTime;
    /**
     * 備考
     */
    private String memo;
}
