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
     * ユーザーID
     */
    private Integer userId;
    /**
     * 承認者ID
     */
    private Integer approverId;
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
