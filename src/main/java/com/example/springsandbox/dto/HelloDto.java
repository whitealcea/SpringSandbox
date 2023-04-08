package com.example.springsandbox.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HelloDto {
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
     * 部署名
     **/
    private String departmentName;
}
