
package com.ngv.api.v1.controller;

import com.ngv.api.v1.dto.ContractDto;
import com.ngv.api.v1.dto.ContractStatusDto;
import com.ngv.api.v1.service.ContractService;
import com.ngv.base.annotations.DateTimeValidator;
import com.ngv.base.constant.DateConstant;
import com.ngv.base.data.ResponseData;
import com.ngv.base.data.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contract")
@RequiredArgsConstructor
@Validated
public class ContractController {

  private final ContractService contractService;


  @GetMapping("/")
  @Operation(summary = "HĐ nhà Cung cấp ngoài")
  public ResponseEntity<ResponseData<ContractDto>> getContract(@RequestParam("fromDate")
  @DateTimeValidator(pattern = DateConstant.STR_PLAN_DD_MM_YYYY_STROKE) String fromDate) {
    return ResponseUtils.success(contractService.getContract(fromDate));
  }

  @GetMapping("/status")
  @Operation(summary = "Lấy trạng thái HĐ")
  public ResponseEntity<ResponseData<ContractStatusDto>> getContractStatus
      (@RequestParam("fromDate")
      @DateTimeValidator(pattern = DateConstant.STR_PLAN_DD_MM_YYYY_STROKE) String fromDate) {
    return ResponseUtils.success(contractService.getContractStatus(fromDate));
  }
}
