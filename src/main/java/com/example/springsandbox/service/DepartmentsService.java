package com.example.springsandbox.service;

import com.example.springsandbox.dto.DepartmentDto;
import com.example.springsandbox.dto.EmployeeDto;
import com.example.springsandbox.entity.Departments;
import com.example.springsandbox.entity.custom.CustomEmployee;
import com.example.springsandbox.mapper.DepartmentsMapper;
import com.example.springsandbox.mapper.custom.CustomEmployeesMapper;
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
    private DepartmentsMapper sampleMapper;
    @NonNull
    private ModelMapper modelMapper;

    public List<DepartmentDto> getDepartmentList() {
        List<Departments> allDepartments = sampleMapper.findAllDepartments();//Select
        return allDepartments.stream().map(department -> modelMapper.map(department, DepartmentDto.class)).collect(Collectors.toList());
    }
}
