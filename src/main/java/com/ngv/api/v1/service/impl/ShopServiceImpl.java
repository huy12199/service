package com.ngv.api.v1.service.impl;

import com.ngv.api.v1.dto.ShoppingLimitDto;
import com.ngv.api.v1.repository.dao.ShopDAO;
import com.ngv.api.v1.service.ShopService;
import com.ngv.base.constant.DateConstant;
import com.ngv.base.data.BaseService;
import com.ngv.base.utils.DateUtils;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShopServiceImpl extends BaseService implements ShopService {

  private final ShopDAO shopDAO;

  @Override
  public ShoppingLimitDto getLimitShopping( String year, String buildingId) {
    String logPrefix = "getLimitShopping";
    LOGGER.info(logPrefix + "|year|" + year + "|buildingId|" + buildingId);
    LocalDate lastDate = LocalDate.of(Integer.parseInt(year), 12, 31);
    String lastDateStr = DateUtils.convertLocalDateTimeToString(
        DateConstant.STR_PLAN_MM_DD_YYYY_STROKE, lastDate.atStartOfDay());
    shopDAO.getShoppingLimit(lastDateStr, year);
    return null;
  }
}
