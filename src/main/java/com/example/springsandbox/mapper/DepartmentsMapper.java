package com.example.springsandbox.mapper;

import com.example.springsandbox.entity.Departments;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentsMapper {
    void insertDepartment(Departments departments);

    void updateDepartment(Departments departments);

    List<Departments> findAllDepartments();
}
