package com.example.springsandbox.service;

import com.example.springsandbox.dto.EmployeeDto;
import com.example.springsandbox.entity.Attendance;
import com.example.springsandbox.entity.Employees;
import com.example.springsandbox.entity.custom.CustomEmployee;
import com.example.springsandbox.mapper.AttendanceMapper;
import com.example.springsandbox.mapper.EmployeesMapper;
import com.example.springsandbox.mapper.custom.CustomEmployeesMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    @NonNull
    private EmployeesMapper employeesMapper;
    @NonNull
    private CustomEmployeesMapper customEmployeeMapper;
    @NonNull
    private ModelMapper modelMapper;
    @NonNull
    private AttendanceMapper attendanceMapper;

    public List<EmployeeDto> getEmployeeList() {
        List<CustomEmployee> allEmployees = customEmployeeMapper.findAllEmployees();
        List<EmployeeDto> list = new ArrayList<>();
        for (CustomEmployee employee : allEmployees) {
            EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);

            Integer employeeId = employee.getId();
            List<Attendance> attendanceList = attendanceMapper.getAttendanceByEmployeeId(employeeId);
            Duration workTimeSummary = Duration.ZERO;

            for (Attendance att : attendanceList) {
                LocalTime endTime = att.getEndTime();
                LocalTime startTime = att.getStartTime();
                LocalTime breakTime = att.getBreakTime();
                Duration workTimeOneday = Duration.between(startTime, endTime);
                if (workTimeOneday.isNegative()) {
                    workTimeOneday = workTimeOneday.plusHours(24);
                }
                Duration breakDuration = Duration.between(LocalTime.MIN, breakTime);
                workTimeOneday = workTimeOneday.minus(breakDuration);
                workTimeSummary = workTimeSummary.plus(workTimeOneday);
            }
            long minutes = workTimeSummary.toMinutes();
            final int MINUTES_PER_HOUR = 60;
            String result = String.format("%02d:%02d", minutes / MINUTES_PER_HOUR, minutes % MINUTES_PER_HOUR);
            dto.setWorkingTimeSummary(result);
            list.add(dto);
        }
        return list;
    }

    public EmployeeDto getEmployeeDetail(Integer employeeId) {
        CustomEmployee employee = customEmployeeMapper.findEmployeeById(employeeId);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public void saveEmployee(EmployeeDto dto) {
        Employees entity = modelMapper.map(dto, Employees.class);
        employeesMapper.insertEmployee(entity);
    }

    public void updateEmployee(EmployeeDto dto) {
        Employees entity = modelMapper.map(dto, Employees.class);
        employeesMapper.updateEmployee(entity);
    }
}
