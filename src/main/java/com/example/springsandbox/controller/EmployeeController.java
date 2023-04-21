package com.example.springsandbox.controller;

import com.example.springsandbox.dto.AttendanceDto;
import com.example.springsandbox.dto.DepartmentDto;
import com.example.springsandbox.dto.EmployeeDto;
import com.example.springsandbox.form.EmployeeForm;
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
@RequestMapping("/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {
    @NonNull
    private EmployeeService employeeService;
    @NonNull
    private DepartmentsService departmentsService;
    @NonNull
    private ModelMapper modelMapper;

    @RequestMapping("/{employeeId}")
    public String employee(@PathVariable Integer employeeId, Model model) {
        EmployeeDto employee = employeeService.getEmployeeDetail(employeeId);
        model.addAttribute("employee", employee);
//        取得したIDの従業員の勤怠情報を取得してHTMLに渡す
        String monthOfAttendance = "2023/01";
        List<AttendanceDto> employeeAttendance = employeeService.getEmployeeAttendance(employeeId, monthOfAttendance);
        model.addAttribute("employeeAttendance", employeeAttendance);
        return "employees/detail";
    }

    @GetMapping("/{employeeId}/update")
    public String showUpdateEmployeeForm(@PathVariable Integer employeeId, Model model) {
        EmployeeDto employee = employeeService.getEmployeeDetail(employeeId);
        model.addAttribute("employee", employee);
        List<DepartmentDto> departmentList = departmentsService.getDepartmentList();
        model.addAttribute("departments", departmentList);
        model.addAttribute("employeeUpdate", new EmployeeForm());
        return "employees/update";
    }

    @PostMapping("/{employeeId}")
    public String updateEmployee(@ModelAttribute("{employee}") EmployeeForm form) {
        EmployeeDto dto = modelMapper.map(form, EmployeeDto.class);
        employeeService.updateEmployee(dto);
        return "redirect:/employee";
    }

    @RequestMapping
    public String employees(Model model) {
        List<EmployeeDto> employeeList = employeeService.getEmployeeList();
        model.addAttribute("employees", employeeList);
        return "employees";
    }

    @GetMapping("/new")
    public String showNewEmployeeForm(Model model) {
        List<DepartmentDto> departmentList = departmentsService.getDepartmentList();
        model.addAttribute("departments", departmentList);
        model.addAttribute("employee", new EmployeeForm());
        return "employees/new";
    }

    @PostMapping
    public String addNewEmployee(@ModelAttribute("employee") EmployeeForm form) {
        EmployeeDto dto = modelMapper.map(form, EmployeeDto.class);
        employeeService.saveEmployee(dto);
        return "redirect:/employee";
    }
}
