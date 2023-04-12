package com.example.springsandbox.service;

import com.example.springsandbox.dto.DepartmentDto;
import com.example.springsandbox.entity.Departments;
import com.example.springsandbox.mapper.DepartmentsMapper;
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

    public List<DepartmentDto> getDepartmentList() {
        List<Departments> allDepartments = departmentsMapper.findAllDepartments();//Select
        return allDepartments.stream().map(department -> modelMapper.map(department, DepartmentDto.class)).collect(Collectors.toList());
    }
}
