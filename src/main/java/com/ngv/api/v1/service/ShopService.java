package com.ngv.api.v1.service;

import com.ngv.api.v1.dto.ShoppingLimitDto;

public interface ShopService {

  ShoppingLimitDto getLimitShopping(String year, String buildingId);
}
