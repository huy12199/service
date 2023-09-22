package com.ngv.api.v1.controller;

import com.ngv.api.v1.repository.dao.EmployeeDAO;
import com.ngv.api.v1.service.EmployeeService;
import com.ngv.base.data.ResponseData;
import com.ngv.base.data.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Employee")
@CrossOrigin("*")
@RequiredArgsConstructor
@Validated
public class EmployeeController {
    private EmployeeService employeeService;
    @GetMapping("/DigitalHardTea") //DIGITAL HARD TEA
    @Operation(summary = "Tra cứu danh bạ")
    public ResponseEntity<ResponseData<EmployeeDAO>> getDigitalHardTea(
            @RequestParam("Department") String Department,
            @RequestParam("StringInput") String StringInput){
            return ResponseUtils.success(employeeService.getEmployee(Department, StringInput));
    }


}
