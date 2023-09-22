package com.ngv.api.v1.service;

import com.ngv.api.v1.dto.ProviderDto;
import java.util.List;

public interface ProviderService extends CommonService {

  List<ProviderDto> getListProvider(String fromDate);

  ProviderDto getProviderById(Long id);

}
