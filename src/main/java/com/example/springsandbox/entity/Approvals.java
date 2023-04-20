package com.example.springsandbox.entity;

import lombok.Data;

/**
 * 承認 Entityクラス
 */
@Data
public class Approvals {
    /**
     * 承認ID
     */
    private Integer id;
    /**
     * 従業員ID
     */
    private Integer employeeId;
    /**
     * 承認者従業員ID
     */
    private Integer approverEmployeeId;
    /**
     * 承認種別
     */
    private String requestType;
    /**
     * 承認内容
     */
    private String requestDetail;
    /**
     * 承認ステータス
     */
    private String approvalStatus;
}
