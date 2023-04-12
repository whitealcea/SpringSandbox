package com.example.springsandbox.mapper;

import com.example.springsandbox.entity.Employees;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeesMapper {

    void insertEmployee(Employees employees);

    List<Employees> findAllEmployees();
}
