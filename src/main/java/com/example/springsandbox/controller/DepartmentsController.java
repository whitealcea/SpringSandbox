package com.example.springsandbox.controller;

import com.example.springsandbox.dto.DepartmentDto;
import com.example.springsandbox.service.DepartmentsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/department")
@Slf4j
@RequiredArgsConstructor
public class DepartmentsController {
    @NonNull
    private DepartmentsService departmentService;

    @RequestMapping("/{departmentId}")
    public String department(@PathVariable Integer departmentId, Model model) {
        DepartmentDto department = departmentService.getDepartmentDetail(departmentId);
        model.addAttribute("department", department);
        return "departments/detail";
    }

    @RequestMapping
    public String departments(Model model) {
        List<DepartmentDto> departmentList = departmentService.getDepartmentList();
        model.addAttribute("departments", departmentList);
        return "departments";
    }
}
