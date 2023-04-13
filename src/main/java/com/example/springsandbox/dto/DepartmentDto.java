package com.example.springsandbox.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DepartmentDto {
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
    /**
     * 従業員の名前
     **/
    private Integer employeeId;

}
