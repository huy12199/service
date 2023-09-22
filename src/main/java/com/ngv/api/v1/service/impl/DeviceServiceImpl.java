package com.ngv.api.v1.service.impl;

import com.ngv.api.v1.dto.DeviceDto;
import com.ngv.api.v1.repository.dao.DeviceDAO;
import com.ngv.api.v1.service.DeviceService;
import com.ngv.base.data.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl extends BaseService implements DeviceService {

  private final DeviceDAO deviceDAO;


  @Override
  public List<DeviceDto> getDeviceGroup(String date) {
    String logPrefix = "getDeviceGroup";
    LOGGER.info(logPrefix + "|date|" + date);
    return deviceDAO.getDeviceGroup(date);
  }
  @Override
  public List<DeviceDto> getDeviceGroupById(Long id) {
    String logPrefix = "getDeviceGroupById";
    LOGGER.info(logPrefix + "|id|" + id);
    return deviceDAO.getDeviceGroupById(id);
  }
  @Override
  public String handleMessageQueue(Object msg) {
    String logPrefix = "handleMessageQueue";
    LOGGER.info(logPrefix + "|msg|" + msg);
    String[] data = msg.toString().split(":");
    List<DeviceDto> deviceDtoList = getDeviceGroupById(Long.valueOf(data[1]));
    String url = "";
    HttpHeaders headers = getHttpHeaders();
    HttpEntity<Object> eHeader = new HttpEntity<Object>(deviceDtoList, headers);
    return restClient.execute(url, HttpMethod.POST, eHeader, String.class);

  }
}
