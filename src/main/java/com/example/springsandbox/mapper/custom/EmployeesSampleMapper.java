package com.example.springsandbox.mapper.custom;

import com.example.springsandbox.entity.custom.EmployeeSample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeesSampleMapper {
    List<EmployeeSample> findAllEmployees();
}
