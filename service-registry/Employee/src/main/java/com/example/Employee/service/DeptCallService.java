package com.example.Employee.service;

import com.example.Employee.config.APIFeignClient;
import com.example.Employee.dto.DepartmentDto;
import com.example.Employee.dto.EmployeeDto;
import com.example.Employee.dto.ResponseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DeptCallService {

    private static List<EmployeeDto> empList = new ArrayList<>();
    RestTemplate restTemplate;
    WebClient webClient;
    APIFeignClient feignClient;

    @Autowired
    public DeptCallService(RestTemplate restTemplate, WebClient webClient, APIFeignClient feignClient) {
        this.restTemplate = restTemplate;
        this.webClient = webClient;
        this.feignClient = feignClient;
        empList.add(EmployeeDto.builder().empName("Ramesh").deptCode("001").empCode("1").build());
        empList.add(EmployeeDto.builder().empName("Suresh").deptCode("002").empCode("2").build());
        empList.add(EmployeeDto.builder().empName("Naresh").deptCode("003").empCode("3").build());
    }

    //@CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getDefaultDept")
    @Retry(name="${spring.application.name}", fallbackMethod = "getDefaultDept")
    public DepartmentDto getSingleDto(String empId) {
        log.info("Inside getSingleDto");
        List<EmployeeDto> list = empList.stream().filter((dto) -> dto.getEmpCode().equals(empId))
                .collect(Collectors.toList());
        DepartmentDto dto = webClient.get().uri("http://localhost:8081/dept/info/" + list.get(0).getDeptCode())
                .retrieve().bodyToMono(DepartmentDto.class).block(); // block to make it blocking

        return dto;
    }

    public DepartmentDto getDefaultDept(String empId, Throwable t) {
        List<EmployeeDto> list = empList.stream().filter((dto) -> dto.getEmpCode().equals(empId))
                .collect(Collectors.toList());
        log.info("Inside getDefaultDept");
        DepartmentDto dto = new DepartmentDto();
        dto.setName("Default");
        dto.setCode("100");
        return dto;
    }
}
