package com.example.springsandbox.mapper;


import com.example.springsandbox.entity.Attendance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttendanceMapper {
    List<Attendance> getAttendanceByEmployeeId(Integer employeeId);
}
