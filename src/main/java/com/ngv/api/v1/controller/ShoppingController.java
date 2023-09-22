
package com.ngv.api.v1.controller;

import com.ngv.api.v1.dto.ShoppingLimitDto;
import com.ngv.api.v1.service.ShopService;
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
@RequestMapping("/shopping")
@RequiredArgsConstructor
@Validated
public class ShoppingController {

  private final ShopService shopService;

  @GetMapping("/limit")
  @Operation(summary = "Hạn mức mua sắm")
  public ResponseEntity<ResponseData<ShoppingLimitDto>> getLimitShopping(
      @RequestParam("year") String year,
      @RequestParam("buildingId") String buildingId) {
    return ResponseUtils.success(shopService.getLimitShopping(year, buildingId));
  }
}
