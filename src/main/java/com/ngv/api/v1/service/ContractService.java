package com.ngv.api.v1.service;

import com.ngv.api.v1.dto.ContractDto;
import com.ngv.api.v1.dto.ContractStatusDto;

public interface ContractService extends CommonService {

  ContractDto getContractById(Long id);
  ContractDto getContract(String fromDate);

  ContractStatusDto getContractStatus(String fromDate);
  ContractStatusDto getContractStatusById(Long id);
}
