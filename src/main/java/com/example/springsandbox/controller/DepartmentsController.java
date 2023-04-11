package com.example.springsandbox.controller;

import com.example.springsandbox.dto.DepartmentDto;
import com.example.springsandbox.dto.EmployeeDto;
import com.example.springsandbox.service.DepartmentsService;
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
@RequestMapping("/department")
@Slf4j
@RequiredArgsConstructor
public class DepartmentsController {
    @NonNull
    private DepartmentsService departmentService;

    @RequestMapping("/{departmentId}")
    public String department(@PathVariable String departmentId) {
        // TODO: departmentId を利用して社員情報を取得して画面に返却する
        //  DepartmentDto emp = departmentService.findDepartmentById(departmentId);

        // FIXME: departmentId が取得できていることを確認できたら削除する
        log.info("DepartmentId を取得：{}", departmentId);
        // TODO: department.html を用意する
        return "department";
    }

    @RequestMapping
    public String departments(Model model) {
        List<DepartmentDto> departmentList = departmentService.getDepartmentList();
        model.addAttribute("departments", departmentList);
        return "departments";
    }
}
