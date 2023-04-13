package com.example.springsandbox.mapper.custom;

import com.example.springsandbox.entity.custom.CustomDepartments;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CustomDepartmentsMapper {
    CustomDepartments findDepartmentById(Integer departmentId);
}
