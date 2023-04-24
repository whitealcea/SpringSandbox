package com.example.springsandbox.service;

import com.example.springsandbox.dto.AttendanceDto;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
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
//            取得した従業員IDから、Attendance型でIDごとの勤怠情報をDBからリストで取得する
            Integer employeeId = employee.getId();
            List<Attendance> attendanceList = attendanceMapper.getAttendanceByEmployeeId(employeeId);
//            IDごとの勤怠情報リストから、1日ごとの勤務時間を算出する処理をリスト分繰り返し、合計勤務時間を算出する
            Duration workTimeSummary = Duration.ZERO;
            for (Attendance att : attendanceList) {
                Duration workTimeOneDay = calculateWorkTimeOneDay(att);
                workTimeSummary = workTimeSummary.plus(workTimeOneDay);
            }
//            表示方法を「〇〇：〇〇」に変換
            String result = durationToString(workTimeSummary);
            dto.setWorkingTimeSummary(result);
            list.add(dto);
        }
        return list;
    }

    public EmployeeDto getEmployeeDetail(Integer employeeId) {
        CustomEmployee employee = customEmployeeMapper.findEmployeeById(employeeId);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public List<AttendanceDto> getEmployeeAttendance(Integer employeeId, @NonNull String monthOfAttendance) {
        List<Attendance> employeeAttendance = attendanceMapper.getAttendanceByEmployeeId(employeeId);
        List<AttendanceDto> list = new ArrayList<>();
//        YearMonth型を使って、年/月から月末日と日付を出す
        YearMonth targetYM = YearMonth.parse(monthOfAttendance);
        for (int i = 1; i <= targetYM.lengthOfMonth(); i++) {
            LocalDate date = targetYM.atDay(i);
            AttendanceDto dto = new AttendanceDto();
            dto.setDate(date);
            LocalTime breakTime = LocalTime.MIN;
            dto.setBreakTime(breakTime);
            dto.setWorkingTime("00:00");
//            dateと一致するdateをAttendanceリストが持っていた場合、そのデータを入れる
            for (Attendance att : employeeAttendance) {
                if (date.equals(att.getDate())) {
                    dto = modelMapper.map(att, AttendanceDto.class);
//                    稼働時間の計算
                    Duration workTimeOneDay = calculateWorkTimeOneDay(att);
                    String result = durationToString(workTimeOneDay);
                    dto.setWorkingTime(result);
                    break;
                }
            }
            list.add(dto);
        }
        return list;
    }

    public Duration calculateWorkTimeOneDay(Attendance att) {
        LocalTime endTime = att.getEndTime();
        LocalTime startTime = att.getStartTime();
        LocalTime breakTime = att.getBreakTime();
        Duration workTimeOneDay = Duration.between(startTime, endTime);
        if (workTimeOneDay.isNegative()) {
            final int dailyTime = 24;
            workTimeOneDay = workTimeOneDay.plusHours(dailyTime);
        }
        Duration breakDuration = Duration.between(LocalTime.MIN, breakTime);
        workTimeOneDay = workTimeOneDay.minus(breakDuration);
        return workTimeOneDay;
    }

    public String durationToString(Duration duration) {
        long minutes = duration.toMinutes();
        final int MINUTES_PER_HOUR = 60;
        return String.format("%02d:%02d", minutes / MINUTES_PER_HOUR, minutes % MINUTES_PER_HOUR);
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
