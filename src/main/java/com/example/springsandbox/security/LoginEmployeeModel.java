package com.example.springsandbox.security;

import com.example.springsandbox.entity.Employees;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

@EqualsAndHashCode(callSuper = false)
public class LoginEmployeeModel extends User {
    @Getter
    private final Employees loginEmp;

    public LoginEmployeeModel(Employees emp, String role) {
        super(emp.getName(), emp.getPassword(), AuthorityUtils.createAuthorityList(role));
        this.loginEmp = emp;
    }
}
