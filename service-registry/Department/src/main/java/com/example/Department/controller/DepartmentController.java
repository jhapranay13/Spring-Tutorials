package com.example.Department.controller;

import com.example.Department.dto.DepartmentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("dept")
public class DepartmentController {

    private static List<DepartmentDto> deptList = new ArrayList<>();

    public DepartmentController() {
        deptList.add(DepartmentDto.builder().name("Computer").code("001").build());
        deptList.add(DepartmentDto.builder().name("Math").code("002").build());
        deptList.add(DepartmentDto.builder().name("Physics").code("003").build());

    }

    @GetMapping(path = "info/{deptId}")
    public ResponseEntity<DepartmentDto> getDeptInfo(@PathVariable("deptId") String deptId ) {
        List<DepartmentDto> dtoList =
                deptList.stream().filter((currdDto) -> currdDto.getCode().equals(deptId)).collect(Collectors.toList());
        return new ResponseEntity(deptList.get(0), HttpStatus.OK);
    }

    @GetMapping(path = "all")
    public ResponseEntity<List<DepartmentDto>> getDeptInfo() {
          return new ResponseEntity(deptList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> getDeptInfoBody(@RequestBody DepartmentDto dto ) {
        deptList.add(dto);
        return new ResponseEntity("Department Added", HttpStatus.OK);
    }
}
