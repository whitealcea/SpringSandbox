package com.example.springsandbox.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 勤怠 Dtoクラス
 */
@Data
public class AttendanceDto {
    /**
     * 勤怠ID
     */
    private Integer id;
    /**
     * ユーザーID
     */
    private Integer userId;
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
     * 勤務時間
     **/
    private String workingTime;
}
