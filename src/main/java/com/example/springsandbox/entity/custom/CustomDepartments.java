package com.example.springsandbox.entity.custom;

import lombok.Data;

@Data
public class CustomDepartments {
    /**
     * 部署ID
     **/
    private Integer id;
    /**
     * 部署名
     **/
    private String departmentName;
    /**
     * 部署番号
     **/
    private String departmentNumber;
    /**
     * 上司の名前
     **/
    private String supervisorName;
    /**
     * 所属する人数
     **/
    private Integer departmentPeople;
}
