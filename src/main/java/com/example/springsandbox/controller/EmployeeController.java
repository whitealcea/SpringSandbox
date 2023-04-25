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
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@Controller
@RequestMapping("/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {
    public static final String YEAR_MONTH_REGEX = "^20[0-9]{2}-(0[1-9]|1[0-2])$";
    @NonNull
    private EmployeeService employeeService;
    @NonNull
    private DepartmentsService departmentsService;
    @NonNull
    private ModelMapper modelMapper;

    @GetMapping("/{employeeId}")
    public String employee(@PathVariable Integer employeeId, @RequestParam(name = "target_ym", required = false) String targetYM, Model model) {
        if (StringUtils.isEmpty(targetYM) || !targetYM.matches(YEAR_MONTH_REGEX)) {
            targetYM = YearMonth.now().toString();
        }
        EmployeeDto employee = employeeService.getEmployeeDetail(employeeId);
        model.addAttribute("employee", employee);
//        取得したIDの従業員の勤怠情報を取得してHTMLに渡す
        List<AttendanceDto> employeeAttendance = employeeService.getEmployeeAttendance(employeeId, targetYM);
        model.addAttribute("employeeAttendance", employeeAttendance);
        model.addAttribute("targetYM", targetYM);
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
