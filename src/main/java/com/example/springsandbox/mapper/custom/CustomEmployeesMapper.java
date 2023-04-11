package com.example.springsandbox.mapper.custom;

import com.example.springsandbox.entity.custom.CustomEmployee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomEmployeesMapper {
    List<CustomEmployee> findAllEmployees();
}
