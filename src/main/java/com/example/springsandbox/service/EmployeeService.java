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
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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
//                まずはAttendanceに取得してあるデータを、ゲッターで使えるようにする
                LocalTime endTime = att.getEndTime();
                LocalTime startTime = att.getStartTime();
                LocalTime breakTime = att.getBreakTime();
//                「Duration」を使って1日ごとの勤務時間を算出し、最後に合計勤務時間に足して１ループが終わる
                Duration workTimeOneday = Duration.between(startTime, endTime);
                if (workTimeOneday.isNegative()) {
                    workTimeOneday = workTimeOneday.plusHours(24);
                }
                Duration breakDuration = Duration.between(LocalTime.MIN, breakTime);
                workTimeOneday = workTimeOneday.minus(breakDuration);
                workTimeSummary = workTimeSummary.plus(workTimeOneday);
            }
//            表示方法を「〇〇：〇〇」に変換
            long minutes = workTimeSummary.toMinutes();
            final int MINUTES_PER_HOUR = 60;
            String result = String.format("%02d:%02d", minutes / MINUTES_PER_HOUR, minutes % MINUTES_PER_HOUR);
//            名前とか住所とかを詰めてる「dto」にセッターで値をセット
            dto.setWorkingTimeSummary(result);
//            名前とか色々（36行目）と、合計勤務時間(37～57行目)の値がdtoに入ったので、Listに1項目として詰める
            list.add(dto);
        }
        return list;
    }

    public EmployeeDto getEmployeeDetail(Integer employeeId) {
        CustomEmployee employee = customEmployeeMapper.findEmployeeById(employeeId);
        return modelMapper.map(employee, EmployeeDto.class);
    }

    public List<AttendanceDto> getEmployeeAttendance(Integer employeeId, String monthOfAttendance) {
        List<Attendance> employeeAttendance = attendanceMapper.getAttendanceByEmployeeId(employeeId);
        List<AttendanceDto> list = new ArrayList<>();
//        コントローラーから受け取った月の、1日から順に勤怠情報をセットする

//        扱いやすいように、年+月を年+月+日(1)に変更(これしないとfor文の中の処理がわからなかった)
        monthOfAttendance = monthOfAttendance + "/01";
//        受け取った年/月/日をLocalDate型に変更(LocalDate型のメソッドを使って、月末日を出す為)
        LocalDate targetMonth = LocalDate.parse(monthOfAttendance, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
//        LocalDate型のメソッドを使って月末日を出す
        LocalDate monthLastDay = targetMonth.with(TemporalAdjusters.lastDayOfMonth());
//        monthLastDay(2023-01-31)のうち、末日の数字だけを抜き出してint型に(for文の繰り返し条件で使用)
        int LastDayOfMonth = monthLastDay.getDayOfMonth();

        for (int i = 0; i < LastDayOfMonth; i++) {
            LocalDate date = targetMonth.plusDays(i);
            AttendanceDto dto = new AttendanceDto();

            dto.setDate(date);
            LocalTime breakTime = LocalTime.of(00, 00);
            dto.setBreakTime(breakTime);
            dto.setWorkingTime("00:00");
//            dateと一致するdateをAttendanceリストが持っていた場合、そのデータを入れる
            for (Attendance att : employeeAttendance) {
                if (date.equals(att.getDate())) {
                    dto = modelMapper.map(att, AttendanceDto.class);
//                    稼働時間の計算
                    LocalTime endTime = att.getEndTime();
                    LocalTime startTime = att.getStartTime();
                    breakTime = att.getBreakTime();
                    Duration workTimeOneday = Duration.between(startTime, endTime);
                    if (workTimeOneday.isNegative()) {
                        workTimeOneday = workTimeOneday.plusHours(24);
                    }
                    Duration breakDuration = Duration.between(LocalTime.of(0, 0), breakTime);
                    workTimeOneday = workTimeOneday.minus(breakDuration);
                    long minutes = workTimeOneday.toMinutes();
                    String result = String.format("%02d:%02d", minutes / 60, minutes % 60);
                    dto.setWorkingTime(result);
                    break;
                }
            }
//           リストに1項目としていれる　list.add(dto)
            list.add(dto);
        }
        return list;
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
