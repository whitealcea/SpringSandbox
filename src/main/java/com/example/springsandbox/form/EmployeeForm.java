package com.example.springsandbox.form;

import lombok.Data;

@Data
public class EmployeeForm {
    /**
     * 従業員ID
     **/
    private Integer id;
    /**
     * 名前
     **/
    private String name;
    /**
     * 住所
     **/
    private String address;
    /**
     * 電話番号
     **/
    private String phoneNumber;
    /**
     * メールアドレス
     **/
    private String email;
    /**
     * 部署ID
     **/
    private Integer departmentId;
}
