package com.example.springsandbox.form;

import lombok.Data;

@Data
public class DepartmentForm {
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

}
