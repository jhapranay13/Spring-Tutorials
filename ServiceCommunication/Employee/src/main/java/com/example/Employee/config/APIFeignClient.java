package com.example.Employee.config;

import com.example.Employee.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://localhost:8081/dept", value = "CLIENT-DEPARTMENT-SERVICE")
public interface APIFeignClient {

    @GetMapping(value = "info/{deptId}")
    DepartmentDto getDepartmentInfo(@PathVariable("deptId") String deptId);

    @GetMapping(value = "all")
    List<DepartmentDto> getDepartmentInfo();

    @PostMapping
    String addDepartment(@RequestBody DepartmentDto dto);
}
