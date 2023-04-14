package com.example.springsandbox.service;

import com.example.springsandbox.dto.DepartmentDto;
import com.example.springsandbox.entity.Departments;
import com.example.springsandbox.entity.custom.CustomDepartments;
import com.example.springsandbox.mapper.DepartmentsMapper;
import com.example.springsandbox.mapper.custom.CustomDepartmentsMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentsService {
    @NonNull
    private DepartmentsMapper departmentsMapper;
    @NonNull
    private ModelMapper modelMapper;
    @NonNull
    private CustomDepartmentsMapper customDepartmentsMapper;


    public List<DepartmentDto> getDepartmentList() {
        List<Departments> allDepartments = departmentsMapper.findAllDepartments();
        return allDepartments.stream().map(department -> modelMapper.map(department, DepartmentDto.class)).collect(Collectors.toList());
    }

    public DepartmentDto getDepartmentDetail(Integer departmentId) {
        CustomDepartments department = customDepartmentsMapper.findDepartmentById(departmentId);
        return modelMapper.map(department, DepartmentDto.class);
    }

    public void saveDepartment(DepartmentDto dto) {
        Departments entity = modelMapper.map(dto, Departments.class);
        departmentsMapper.insertDepartment(entity);
    }
}
