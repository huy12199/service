package com.ngv.api.v1.service.impl;

import com.ngv.api.v1.dto.ProviderDto;
import com.ngv.api.v1.repository.dao.ProviderDAO;
import com.ngv.api.v1.service.ProviderService;
import com.ngv.base.data.BaseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl extends BaseService implements ProviderService {

  private final ProviderDAO providerDAO;


  @Override
  public List<ProviderDto> getListProvider(String date) {
    String logPrefix = "getListProvider";
    LOGGER.info(logPrefix + "|date|" + date);
    return providerDAO.getListProvider(date);
  }

  @Override
  public ProviderDto getProviderById(Long id) {
    String logPrefix = "getProviderById";
    LOGGER.info(logPrefix + "|id|" + id);
    return providerDAO.getProviderById(id);
  }

  @Override
  public String handleMessageQueue(Object msg) {
    String logPrefix = "handleMessageQueue";
    LOGGER.info(logPrefix + "|msg|" + msg);
    String[] data = msg.toString().split(":");
    ProviderDto providerDto = getProviderById(Long.valueOf(data[1]));
    String url = "";
    HttpHeaders headers = getHttpHeaders();
    HttpEntity<Object> eHeader = new HttpEntity<Object>(providerDto, headers);
    return restClient.execute(url, HttpMethod.POST, eHeader, String.class);

  }
}
