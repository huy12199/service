package com.ngv.api.v1.controller;

import com.ngv.base.annotations.DateTimeValidator;
import com.ngv.base.constant.DateConstant;
import com.ngv.base.data.ResponseData;
import com.ngv.base.data.ResponseUtils;
import com.ngv.api.v1.dto.ProviderDto;
import com.ngv.api.v1.service.ProviderService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
@RequiredArgsConstructor
@Validated
public class ProviderController {

  private final ProviderService providerService;

  @GetMapping("/")
  @Operation(summary = "Danh sách nhà cung cấp")
  public ResponseEntity<ResponseData<List<ProviderDto>>> getListProvider(@RequestParam("fromDate")
  @DateTimeValidator(pattern = DateConstant.STR_PLAN_DD_MM_YYYY_STROKE) String fromDate) {
    return ResponseUtils.success(providerService.getListProvider(fromDate));
  }
}
