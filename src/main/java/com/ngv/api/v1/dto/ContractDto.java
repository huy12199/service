package com.ngv.api.v1.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class ContractDto {

  private Long id;
  private String contractNumber;
  private LocalDateTime signDate;
  private String contractCode;
  private Long providerId;
  private Long totalAmount;
  private Long prepaymentAmount;
  private String currencyType;
  private LocalDateTime effectiveDate;
  private LocalDateTime contractExpirationDate;
  private String shipment;
  private String paymentTerm;
  private String status;
  private String reason;
  private LocalDateTime inputDate;

}
