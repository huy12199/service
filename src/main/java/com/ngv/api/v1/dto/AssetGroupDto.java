package com.ngv.api.v1.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AssetGroupDto {
  private Long assetGroupId;
  private String assetGroupCode;
  private String assetGroupName;
  private LocalDateTime updateDate;
  private String assetGroupStatus;

}



