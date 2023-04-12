package com.example.springsandbox.controller;

import com.example.springsandbox.dto.EmployeeDto;
import com.example.springsandbox.service.EmployeeService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employee")
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {
    @NonNull
    private EmployeeService employeeService;

    @RequestMapping("/{employeeId}")
    public String employee(@PathVariable Integer employeeId, Model model) {
        // TODO: employeeId を利用して社員情報を取得して画面に返却する
        //  EmployeeDto emp = employeeService.findEmployeeById(employeeId);

        // FIXME: employeeId が取得できていることを確認できたら削除する
        log.info("EmployeeId を取得：{}", employeeId);

        EmployeeDto employee = employeeService.getEmployeeDetail(employeeId);
        model.addAttribute("employee", employee);

        // TODO: employee.html を用意する
        return "employees/detail";
    }

    @RequestMapping
    public String employees(Model model) {
        List<EmployeeDto> employeeList = employeeService.getEmployeeList();
        model.addAttribute("employees", employeeList);
        return "employees";
    }
}
