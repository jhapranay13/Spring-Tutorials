package com.example.Employee.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentDto {
    private String name;
    private String code;
}
