package com.example.springsandbox.service;

import com.example.springsandbox.dto.HelloDto;
import com.example.springsandbox.entity.custom.EmployeeSample;
import com.example.springsandbox.mapper.custom.EmployeesSampleMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HelloService {
    @NonNull
    private EmployeesSampleMapper sampleMapper;
    @NonNull
    private ModelMapper modelMapper;

    public List<HelloDto> getEmployeeList() {
        List<EmployeeSample> allEmployees = sampleMapper.findAllEmployees();
        return allEmployees.stream().map(employee -> modelMapper.map(employee, HelloDto.class)).collect(Collectors.toList());
    }
}
