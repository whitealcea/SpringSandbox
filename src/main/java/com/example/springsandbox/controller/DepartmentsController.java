package com.example.springsandbox.controller;

import com.example.springsandbox.dto.DepartmentDto;
import com.example.springsandbox.dto.EmployeeDto;
import com.example.springsandbox.form.DepartmentForm;
import com.example.springsandbox.service.DepartmentsService;
import com.example.springsandbox.service.EmployeeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/department")
@Slf4j
@RequiredArgsConstructor
public class DepartmentsController {
    @NonNull
    private DepartmentsService departmentService;
    @NonNull
    private EmployeeService employeeService;
    @NonNull
    private ModelMapper modelMapper;

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

    @GetMapping("/new")
    public String showNewDepartmentForm(Model model) {
        List<EmployeeDto> employeeList = employeeService.getEmployeeList();
        model.addAttribute("employees", employeeList);
        model.addAttribute("department", new DepartmentForm());
        return "departments/new";
    }

    @PostMapping
    public String addNewDepartment(@ModelAttribute("department") DepartmentForm form) {
        DepartmentDto dto = modelMapper.map(form, DepartmentDto.class);
        departmentService.saveDepartment(dto);
        return "redirect:/department";
    }
}
