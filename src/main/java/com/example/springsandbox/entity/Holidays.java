package com.example.springsandbox.entity;

import lombok.Data;

/**
 * 休暇 Entityクラス
 */
@Data
public class Holidays {
    /**
     * 休暇ID
     */
    private Integer id;
    /**
     * 従業員ID
     */
    private Integer employeeId;
    /**
     * 休暇種別
     */
    private String holidayType;
    /**
     * 取得日数
     */
    private Integer takenDays;
    /**
     * 残日数
     */
    private Integer remainingDays;
}
