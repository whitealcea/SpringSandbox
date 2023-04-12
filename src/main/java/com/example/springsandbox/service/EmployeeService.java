package com.example.springsandbox.service;

import com.example.springsandbox.dto.EmployeeDto;
import com.example.springsandbox.entity.custom.CustomEmployee;
import com.example.springsandbox.mapper.custom.CustomEmployeesMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeService {
    @NonNull
    private CustomEmployeesMapper sampleMapper;
    @NonNull
    private ModelMapper modelMapper;

    public List<EmployeeDto> getEmployeeList() {
        List<CustomEmployee> allEmployees = sampleMapper.findAllEmployees();//Select
        return allEmployees.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
    }

    public EmployeeDto getEmployeeDetail(Integer employeeId) {
        CustomEmployee employee = sampleMapper.findEmployeeById(employeeId);
        return modelMapper.map(employee, EmployeeDto.class);
    }

}
