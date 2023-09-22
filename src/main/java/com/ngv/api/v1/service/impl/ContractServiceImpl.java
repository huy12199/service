package com.ngv.api.v1.service.impl;

import com.ngv.api.v1.dto.ContractDto;
import com.ngv.api.v1.dto.ContractStatusDto;
import com.ngv.api.v1.event.Event;
import com.ngv.api.v1.repository.dao.ContractDAO;
import com.ngv.api.v1.service.ContractService;
import com.ngv.base.data.BaseService;
import com.ngv.base.exception.BaseException;
import com.ngv.base.exception.CommonErrorCode;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl extends BaseService implements ContractService {

  private final ContractDAO contractDAO;

  @Override
  public String handleMessageQueue(Object msg) {
    String logPrefix = "handleMessageQueue";
    LOGGER.info(logPrefix + "|msg|" + msg);
    String[] data = msg.toString().split(":");
    if (data[0].equals(Event.CONTRACT.getEventName())) {
      ContractDto contractDto = getContractById(Long.valueOf(data[1]));
      String url = "";
      HttpHeaders headers = getHttpHeaders();
      HttpEntity<Object> eHeader = new HttpEntity<Object>(contractDto, headers);
      return restClient.execute(url, HttpMethod.POST, eHeader, String.class);
    } else {
      ContractStatusDto contractDto = getContractStatusById(Long.valueOf(data[1]));
      String url = "";
      HttpHeaders headers = getHttpHeaders();
      HttpEntity<Object> eHeader = new HttpEntity<Object>(contractDto, headers);
      return restClient.execute(url, HttpMethod.POST, eHeader, String.class);
    }

  }

  @Override
  public ContractDto getContractById(Long id) {
    LOGGER.info("getContractById|" + "contractId|" + id);
    return contractDAO.getContractById(id);
  }

  @Override
  public ContractDto getContract(String date) {
    String logPrefix = "getContract";
    LOGGER.info(logPrefix + "|date|" + date);
    ContractDto contract = contractDAO.getContractByDate(date);
    if (Objects.isNull(contract)) {
      throw new BaseException(CommonErrorCode.CONTRACT_NOT_FOUND);
    }
    return contract;
  }

  @Override
  public ContractStatusDto getContractStatus(String date) {
    String logPrefix = "getContractStatus";
    LOGGER.info(logPrefix + "|date|" + date);
    ContractStatusDto contractStatus = contractDAO.getContractStatus(date);
    if (Objects.isNull(contractStatus)) {
      throw new BaseException(CommonErrorCode.CONTRACT_NOT_FOUND);
    }
    return contractStatus;
  }

  @Override
  public ContractStatusDto getContractStatusById(Long id) {
    String logPrefix = "getContractStatus";
    LOGGER.info(logPrefix + "|id|" + id);
    return contractDAO.getContractStatusById(id);
  }
}
