package com.ngv.api.v1.dto;

import lombok.Data;

@Data
public class ShoppingLimitDto {

  private Long budgetGroupId;
  private String budgetGroupCode;
  private String budgetGroupName;
  private String budgetID;
  private String budgetCode;
  private String budgetName;
  private String budgetItemId;
  private String budgetItemCode;
  private String budgetItemName;
  private Long totalBudget;
  private Long usedBudget;
  private Long remainBudget;

}
