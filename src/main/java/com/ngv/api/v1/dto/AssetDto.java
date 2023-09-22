package com.ngv.api.v1.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class AssetDto {

  private Long assetGroupId;
  private String assetGroupCode;
  private String assetGroupName;
  private Long equipmentGroupId;
  private String equipmentGroupCode;
  private String equipmentGroupName;
  private Long assetId;
  private String assetCode;
  private String model;
  private String seri;
  private String unit;
  private String quantity;
  private String status;
  private String reason;
  private LocalDate updateDate;

}



