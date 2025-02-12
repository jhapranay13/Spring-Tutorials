package com.example.Employee.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDto {
    DepartmentDto dept;
    EmployeeDto emp;
}
