package com.ngv.api.v1.event;

import com.ngv.base.event.CoreEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author dungnv
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public enum Event implements CoreEvent {
  CONTRACT("CONTRACT", "contractServiceImpl", "handleMessageQueue"),
  CONTRACT_STATUS("CONTRACT-STATUS", "contractServiceImpl", "handleMessageQueue"),
  ASSET_GROUP("ASSET-GROUP", "assetServiceImpl", "handleMessageQueue"),
  ASSET("ASSET", "assetServiceImpl", "handleMessageQueue"),
  ASSET_STATUS("ASSET-STATUS", "assetServiceImpl", "handleMessageQueue"),
  DEVICE_GROUP("DEVICE-GROUP", "deviceServiceImpl", "handleMessageQueue"),
  PROVIDER("PROVIDER", "providerServiceImpl", "handleMessageQueue"),
  ;

  private final String eventName;

  private final String handleEventBeanName;

  private final String handleEventFunctionName;
}
