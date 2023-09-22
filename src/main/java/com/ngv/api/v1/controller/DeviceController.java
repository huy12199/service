package com.ngv.api.v1.controller;

import com.ngv.api.v1.dto.DeviceDto;
import com.ngv.api.v1.service.DeviceService;
import com.ngv.base.annotations.DateTimeValidator;
import com.ngv.base.constant.DateConstant;
import com.ngv.base.data.ResponseData;
import com.ngv.base.data.ResponseUtils;
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
@RequestMapping("/device")
@RequiredArgsConstructor
@Validated
public class DeviceController {

  private final DeviceService deviceService;

  @GetMapping("/group")
  @Operation(summary = "Nhóm thiết bị")
  public ResponseEntity<ResponseData<List<DeviceDto>>> getDeviceGroup(@RequestParam("fromDate")
  @DateTimeValidator(pattern = DateConstant.STR_PLAN_DD_MM_YYYY_STROKE) String fromDate) {
    return ResponseUtils.success(deviceService.getDeviceGroup(fromDate));
  }
}
