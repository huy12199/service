package com.ngv.api.v1.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class DeviceDto {
  private Long equipmentGroupId;
  private Long assetGroupId;
  private String assetGroupCode;
  private String equipmentGroupCode;
  private String equipmentGroupName;
  private LocalDateTime dataDate;
  private String equipmentGroupStatus;

}



