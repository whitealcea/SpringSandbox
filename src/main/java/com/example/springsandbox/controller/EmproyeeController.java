package com.example.springsandbox.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
@Slf4j
public class EmproyeeController {

    @RequestMapping("/{employeeId}")
    public String emproyee(@PathVariable String employeeId) {
        // TODO: employeeId を利用して社員情報を取得して画面に返却する
        //  EmployeeDto emp = employeeService.findEmployeeById(employeeId);

        // FIXME: employeeId が取得できていることを確認できたら削除する
        log.info("EmployeeId を取得：{}", employeeId);
        // TODO: employee.html を用意する
        return "employee";
    }
}
