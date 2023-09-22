package com.ngv.api.v1.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssetStatusDto {

  private Long assetId;
  private String assetCode;
  private String status;
  private String reason;
  private LocalDate updateDate;

}



