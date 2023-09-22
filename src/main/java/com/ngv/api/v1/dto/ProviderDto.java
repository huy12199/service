package com.ngv.api.v1.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ProviderDto {

  private Long providerId;
  private String providerCode;
  private String taxCode;
  private String providerName;
  private String providerAddress;
  private String contactPersonName;
  private String contactMail;
  private String phoneNumber;
  private String reason;
  private LocalDateTime dataDate;
  private String providerStatus;
}
