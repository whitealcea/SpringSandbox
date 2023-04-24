package com.example.springsandbox.security;

import com.example.springsandbox.entity.Employees;
import com.example.springsandbox.mapper.EmployeesMapper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginEmployeeService implements UserDetailsService {

    @NonNull
    private EmployeesMapper employeesMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        //入力された名前をキーにemployeeテーブルのレコードを1件取得
        Employees employee = employeesMapper.findByUserId(userId);

        //該当レコードが取得できなかった場合はエラーにする
        if (employee == null) {
            throw new UsernameNotFoundException("Wrong email or password");
        }

        //ログインユーザー権限を設定
        String role = "ROLE_ADMIN";

        return new LoginEmployeeModel(employee, role);
    }
}
