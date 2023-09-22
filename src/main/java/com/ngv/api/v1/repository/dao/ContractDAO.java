package com.ngv.api.v1.repository.dao;

import com.ngv.api.v1.dto.ContractDto;
import com.ngv.api.v1.dto.ContractStatusDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ContractDAO {

  protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  private final NamedParameterJdbcTemplate jdbcTemplate;

  public ContractDto getContractByDate(String fromDate) {
    String logPrefix = "getContractByDate";
    LOGGER.info(logPrefix + "|fromDate|" + fromDate);
    List<ContractDto> data = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT a.* FROM ( ");
    sql.append("SELECT ");
    sql.append("	CNTR.CNTR_ID id, ");
    sql.append("	CNTR.CNTR_NM contractNumber, ");
    sql.append("	CNTR.CNTR_DT signDate, ");
    sql.append("	CNTR.CNTR_CD contractCode, ");
    sql.append("	PRDR.PRDR_ID providerId, ");
    sql.append("	CNTR.CNTR_TOT_AMT totalAmount, ");
    sql.append("	CNTR.PREP_AMT prepaymentAmount, ");
    sql.append("	CNTR.CY_CD currencyType, ");
    sql.append("	CNTR.EFF_DT effectiveDate, ");
    sql.append("	CNTR.EXPR_DT contractExpirationDate, ");
    sql.append("	CNTR.SHPMNT_INFO shipment, ");
    sql.append("	CNTR.PAY_TRM paymentTerm, ");
    sql.append("	CNTR_STS.CNTR_STS status, ");
    sql.append("	CNTR.CNTR_RSN reason, ");
    sql.append("	CNTR.INPUT_DT inputDate");
    sql.append("FROM ");
    sql.append("	TBPO_PRDR_CNTR_T CNTR ");
    sql.append("JOIN TBPO_PRDR_CNTR_STS_T CNTR_STS ON ");
    sql.append("	CNTR.CNTR_ID = CNTR_STS.CNTR_ID ");
    sql.append("JOIN TBMD_PRDR_M PRDR ON ");
    sql.append("	PRDR.PRDR_ID = CNTR.PRDR_ID ");
    sql.append("WHERE ");
    sql.append("	TRUNC(CNTR.INPUT_DT) >= TO_DATE(:fromDate, 'DD/MM/YYYY') ");
    sql.append("ORDER BY ");
    sql.append("	CNTR.ID, ");
    sql.append("	CNTR.INPUT_DT DESC ");
    sql.append("	)a WHERE rownum =1 ");
    Map<String, Object> mapParams = new HashMap<>();
    mapParams.put("fromDate", fromDate);
    try {
      SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
      data = jdbcTemplate.query(sql.toString(), parameters,
          new BeanPropertyRowMapper<>(ContractDto.class));
      if (CollectionUtils.isNotEmpty(data)) {
        return data.get(0);
      }
    } catch (Exception e) {
      LOGGER.error(logPrefix + "|exception|" + e.getMessage());
    }
    return null;
  }

  public ContractStatusDto getContractStatus(String fromDate) {
    String logPrefix = "getContractStatus";
    LOGGER.info(logPrefix + "|fromDate|" + fromDate);
    List<ContractStatusDto> data = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT a.* FROM ( ");
    sql.append("SELECT ");
    sql.append("	CNTR_STS.CNTR_ID contractId, ");
    sql.append("	CNTR_STS.CNTR_STS contractStatus, ");
    sql.append("	CNTR_STS.CNTR_RSN  reason, ");
    sql.append("	CNTR_STS. INPUT_DT updateData ");
    sql.append("FROM TBPO_PRDR_CNTR_STS_T CNTR_STS ");
    sql.append("WHERE TRUNC(CNTR_STS.INPUT_DT)  >=TO_DATE(:fromDate, 'DD/MM/YYYY') ");
    sql.append("	)a WHERE rownum =1 ");
    Map<String, Object> mapParams = new HashMap<>();
    mapParams.put("fromDate", fromDate);
    try {
      SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
      data = jdbcTemplate.query(sql.toString(), parameters,
          new BeanPropertyRowMapper<>(ContractStatusDto.class));
      if (CollectionUtils.isNotEmpty(data)) {
        return data.get(0);
      }
    } catch (Exception e) {
      LOGGER.error(logPrefix + "|exception|" + e.getMessage());
    }
    return null;
  }


  public ContractDto getContractById(Long contractId) {
    String logPrefix = "getContractById";
    LOGGER.info(logPrefix + "|contractId|" + contractId);
    List<ContractDto> data = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT a.* FROM ( ");
    sql.append("SELECT ");
    sql.append("	CNTR.CNTR_ID id, ");
    sql.append("	CNTR.CNTR_NM contractNumber, ");
    sql.append("	CNTR.CNTR_DT signDate, ");
    sql.append("	CNTR.CNTR_CD contractCode, ");
    sql.append("	PRDR.PRDR_ID providerId, ");
    sql.append("	CNTR.CNTR_TOT_AMT totalAmount, ");
    sql.append("	CNTR.PREP_AMT prepaymentAmount, ");
    sql.append("	CNTR.CY_CD currencyType, ");
    sql.append("	CNTR.EFF_DT effectiveDate, ");
    sql.append("	CNTR.EXPR_DT contractExpirationDate, ");
    sql.append("	CNTR.SHPMNT_INFO shipment, ");
    sql.append("	CNTR.PAY_TRM paymentTerm, ");
    sql.append("	CNTR_STS.CNTR_STS status, ");
    sql.append("	CNTR.CNTR_RSN reason, ");
    sql.append("	CNTR.INPUT_DT inputDate ");
    sql.append("FROM ");
    sql.append("	TBPO_PRDR_CNTR_T CNTR ");
    sql.append("JOIN TBPO_PRDR_CNTR_STS_T CNTR_STS ON ");
    sql.append("	CNTR.CNTR_ID = CNTR_STS.CNTR_ID ");
    sql.append("JOIN TBMD_PRDR_M PRDR ON ");
    sql.append("	PRDR.PRDR_ID = CNTR.PRDR_ID ");
    sql.append("WHERE ");
    sql.append("	CNTR.CNTR_ID =:contractId ");
    sql.append("ORDER BY ");
    sql.append("	CNTR.ID, ");
    sql.append("	CNTR.INPUT_DT DESC ");
    sql.append("	)a WHERE rownum =1 ");
    Map<String, Object> mapParams = new HashMap<>();
    mapParams.put("contractId", contractId);
    try {
      SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
      data = jdbcTemplate.query(sql.toString(), parameters,
          new BeanPropertyRowMapper<>(ContractDto.class));
      if (CollectionUtils.isNotEmpty(data)) {
        return data.get(0);
      }
    } catch (Exception e) {
      LOGGER.error(logPrefix + "|exception|" + e.getMessage());
    }
    return null;
  }

  public ContractStatusDto getContractStatusById(Long id) {
    String logPrefix = "getContractStatusById";
    LOGGER.info(logPrefix + "|id|" + id);
    List<ContractStatusDto> data = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT a.* FROM ( ");
    sql.append("SELECT ");
    sql.append("	CNTR_STS.CNTR_ID contractId, ");
    sql.append("	CNTR_STS.CNTR_STS contractStatus, ");
    sql.append("	CNTR_STS.CNTR_RSN  reason, ");
    sql.append("	CNTR_STS. INPUT_DT updateData ");
    sql.append("FROM TBPO_PRDR_CNTR_STS_T CNTR_STS ");
    sql.append("WHERE CNTR_STS.ID =:id ");
    sql.append("	)a WHERE rownum =1 ");
    Map<String, Object> mapParams = new HashMap<>();
    mapParams.put("id", id);
    try {
      SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
      data = jdbcTemplate.query(sql.toString(), parameters,
          new BeanPropertyRowMapper<>(ContractStatusDto.class));
      if (CollectionUtils.isNotEmpty(data)) {
        return data.get(0);
      }
    } catch (Exception e) {
      LOGGER.error(logPrefix + "|exception|" + e.getMessage());
    }
    return null;
  }
}
