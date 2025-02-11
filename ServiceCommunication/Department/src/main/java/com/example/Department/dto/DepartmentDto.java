package com.example.Department.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DepartmentDto {
    private String name;
    private String code;
}
