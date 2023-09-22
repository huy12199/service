package com.ngv.api.v1.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ContractStatusDto {

  private Long contractId;
  private String contractStatus;
  private String reason;
  private LocalDateTime updateData;

}
