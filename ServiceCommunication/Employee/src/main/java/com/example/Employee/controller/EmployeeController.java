package com.example.Employee.controller;

import com.example.Employee.config.APIFeignClient;
import com.example.Employee.dto.DepartmentDto;
import com.example.Employee.dto.EmployeeDto;
import com.example.Employee.dto.ResponseDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("emp")
public class EmployeeController {

    private static List<EmployeeDto> empList = new ArrayList<>();
    RestTemplate restTemplate;
    WebClient webClient;
    APIFeignClient feignClient;

    @Autowired
    public EmployeeController(RestTemplate restTemplate, WebClient webClient, APIFeignClient feignClient) {
        this.restTemplate = restTemplate;
        this.webClient = webClient;
        this.feignClient = feignClient;
        empList.add(EmployeeDto.builder().empName("Ramesh").deptCode("001").empCode("1").build());
        empList.add(EmployeeDto.builder().empName("Suresh").deptCode("002").empCode("2").build());
        empList.add(EmployeeDto.builder().empName("Naresh").deptCode("003").empCode("3").build());
    }

    @GetMapping(value = "template/{empId}")
    public ResponseEntity<ResponseDto> getTemplateInfo(@PathVariable("empId") String empId) {
        List<EmployeeDto> list = empList.stream().filter((dto) -> dto.getEmpCode().equals(empId))
                .collect(Collectors.toList());
        ResponseEntity<DepartmentDto> dept =
                restTemplate.getForEntity("http://localhost:8081/dept/info/" + list.get(0).getDeptCode(),
                        DepartmentDto.class);
        ResponseDto responseDto =  ResponseDto.builder().emp(list.get(0)).dept(dept.getBody()).build();
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "template")
    public ResponseEntity<List<DepartmentDto>> getTemplateInfo() {
        ResponseEntity<List> dept =
                restTemplate.getForEntity("http://localhost:8081/dept/all",
                        List.class);
        return new ResponseEntity((List<DepartmentDto>)dept.getBody(), HttpStatus.OK);
    }

    @PostMapping(value = "template")
    public ResponseEntity<String> addTemplateInfo(@RequestBody DepartmentDto dto) {
        ResponseEntity<String> message =
                restTemplate.postForEntity("http://localhost:8081/dept", dto,
                        String.class);
        return new ResponseEntity(message.getBody(), HttpStatus.OK);
    }

    @GetMapping(value = "webclient/{empId}")
    public ResponseEntity<ResponseDto> getWebClientInfo(@PathVariable("empId") String empId) {
        List<EmployeeDto> list = empList.stream().filter((dto) -> dto.getEmpCode().equals(empId))
                .collect(Collectors.toList());

        DepartmentDto dto = webClient.get().uri("http://localhost:8081/dept/info/" + list.get(0).getDeptCode())
                .retrieve().bodyToMono(DepartmentDto.class).block(); // block to make it blocking
        ResponseDto responseDto =  ResponseDto.builder().emp(list.get(0)).dept(dto).build();
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "webclient")
    public ResponseEntity<List<DepartmentDto>> getWebClientInfo() {
        List<DepartmentDto> dtoList = webClient.get().uri("http://localhost:8081/dept/all")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<DepartmentDto>>() {}).block(); // block to make it blocking

        return new ResponseEntity(dtoList, HttpStatus.OK);
    }

    @PostMapping(value = "webclient")
    public ResponseEntity<String> addWebClientInfo(@RequestBody DepartmentDto dto) {

        String message = webClient.post().
                uri("http://localhost:8081/dept").bodyValue(dto).
                retrieve().bodyToMono(String.class).block();
        return new ResponseEntity(message, HttpStatus.OK);
    }

    @GetMapping(value = "feign/{empId}")
    public ResponseEntity<ResponseDto> getFeignInfo(@PathVariable("empId") String empId) {
        List<EmployeeDto> list = empList.stream().filter((dto) -> dto.getEmpCode().equals(empId))
                .collect(Collectors.toList());

        DepartmentDto dto = feignClient.getDepartmentInfo(list.get(0).getDeptCode());
        ResponseDto responseDto =  ResponseDto.builder().emp(list.get(0)).dept(dto).build();
        return new ResponseEntity(responseDto, HttpStatus.OK);
    }

    @GetMapping(value = "feign")
    public ResponseEntity<List<DepartmentDto>> getFeignInfo() {
        List<DepartmentDto> dtoList = feignClient.getDepartmentInfo();
        return new ResponseEntity(dtoList, HttpStatus.OK);
    }

    @PostMapping(value = "feign")
    public ResponseEntity<String> addFeignInfo(@RequestBody DepartmentDto dto) {

        String message = feignClient.addDepartment(dto);
        return new ResponseEntity(message, HttpStatus.OK);
    }
}
