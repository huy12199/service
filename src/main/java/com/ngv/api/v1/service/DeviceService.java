package com.ngv.api.v1.service;

import com.ngv.api.v1.dto.DeviceDto;
import java.util.List;

public interface DeviceService extends CommonService {

  List<DeviceDto> getDeviceGroup(String fromDate);

  List<DeviceDto> getDeviceGroupById(Long id);

}
