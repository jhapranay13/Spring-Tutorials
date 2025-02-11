package com.example.Employee.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeDto {
    private String empName;
    private String empCode;
    private String deptCode;
}
