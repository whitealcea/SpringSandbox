package com.example.springsandbox.controller;

import com.example.springsandbox.dto.AttendanceDto;
import com.example.springsandbox.form.AttendanceForm;
import com.example.springsandbox.security.LoginEmployeeModel;
import com.example.springsandbox.service.EmployeeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AttendanceController {
    @NonNull
    private EmployeeService employeeService;
    @NonNull
    private ModelMapper modelMapper;

    @RequestMapping("/attendance")
    public String attendance() {
        return "/attendance";
    }

    @PostMapping("/attendance")
    public String registrationAttendance(@AuthenticationPrincipal LoginEmployeeModel auth, AttendanceForm form) {
        AttendanceDto dto = modelMapper.map(form, AttendanceDto.class);
        dto.setEmployeeId(auth.getLoginEmp().getId());
        employeeService.registrationAttendance(dto);
        return "attendance";
    }
}
