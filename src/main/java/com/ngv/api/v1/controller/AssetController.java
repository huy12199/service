package com.ngv.api.v1.controller;

import com.ngv.api.v1.dto.AssetDto;
import com.ngv.api.v1.dto.AssetGroupDto;
import com.ngv.api.v1.dto.AssetStatusDto;
import com.ngv.api.v1.service.AssetService;
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
@RequestMapping("/asset")
@RequiredArgsConstructor
@Validated
public class AssetController {
  private final AssetService assetService;
  @GetMapping("/group")
  @Operation(summary = "Nhóm tài sản")
  public ResponseEntity<ResponseData<List<AssetGroupDto>>> getAssetGroup(@RequestParam("fromDate")
  @DateTimeValidator(pattern = DateConstant.STR_PLAN_DD_MM_YYYY_STROKE) String date){
    return ResponseUtils.success(assetService.getAssetGroup(date));
  }
  @GetMapping("/")
  @Operation(summary = "Chi tiết tài sản")
  public ResponseEntity<ResponseData<AssetDto>> getAssetDetail(@RequestParam("fromDate")
  @DateTimeValidator(pattern = DateConstant.STR_PLAN_DD_MM_YYYY_STROKE) String date){
    return ResponseUtils.success(assetService.getAssetDetail(date));
  }

  @GetMapping("/status")
  @Operation(summary = "Trạng thái tài sản")
  public ResponseEntity<ResponseData<AssetStatusDto>> getStatusAsset(@RequestParam("fromDate")
  @DateTimeValidator(pattern = DateConstant.STR_PLAN_DD_MM_YYYY_STROKE) String date){
    return ResponseUtils.success(assetService.getStatusAsset(date));
  }
}
