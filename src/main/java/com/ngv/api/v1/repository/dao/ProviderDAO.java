package com.ngv.api.v1.repository.dao;

import com.ngv.api.v1.dto.ProviderDto;
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
public class ProviderDAO {

  protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  private final NamedParameterJdbcTemplate jdbcTemplate;

  public List<ProviderDto> getListProvider(String fromDate) {
    String logPrefix = "getListProvider";
    LOGGER.info(logPrefix + "|fromDate|" + fromDate);
    List<ProviderDto> data = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT  ");
    sql.append("	PRDR.ID providerId	,	");
    sql.append("	PRDR.PRDR_CD providerCode ,	");
    sql.append("	PRDR.PRDR_NM providerName , ");
    sql.append("	PRDR.TX_CD taxCode  , ");
    sql.append("	PRDR.PRDR_ADDR providerAddress , ");
    sql.append("	PRDR.CTCT_NM contactPersonName , ");
    sql.append("	PRDR.CTCT_EMAL contactMail ,");
    sql.append("	PRDR. CTCT_PHN phoneNumber ,");
    sql.append("	PRDR.INPUT_DT dataDate ,");
    sql.append("	PRDR. PRDR_STS providerStatus");
    sql.append(" FROM ");
    sql.append("	TBMD_PRDR_M PRDR ");
    sql.append(" WHERE ");
    sql.append("	TRUNC(PRDR.INPUT_DT) >= TO_DATE(:fromDate, 'DD/MM/YYYY')");
    Map<String, Object> mapParams = new HashMap<>();
    mapParams.put("fromDate", fromDate);
    try {
      SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
      data = jdbcTemplate.query(sql.toString(), parameters,
          new BeanPropertyRowMapper<>(ProviderDto.class));
    } catch (Exception e) {
      LOGGER.error(logPrefix + "|exception|" + e.getMessage());
    }
    return data;
  }

  public ProviderDto getProviderById(Long id) {
    String logPrefix = "getProviderById";
    LOGGER.info(logPrefix + "|id|" + id);
    List<ProviderDto> data = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT  ");
    sql.append("	PRDR.ID providerId	,	");
    sql.append("	PRDR.PRDR_CD providerCode ,	");
    sql.append("	PRDR.PRDR_NM providerName , ");
    sql.append("	PRDR.TX_CD taxCode  , ");
    sql.append("	PRDR.PRDR_ADDR providerAddress , ");
    sql.append("	PRDR.CTCT_NM contactPersonName , ");
    sql.append("	PRDR.CTCT_EMAL contactMail ,");
    sql.append("	PRDR. CTCT_PHN phoneNumber ,");
    sql.append("	PRDR.INPUT_DT dataDate ,");
    sql.append("	PRDR. PRDR_STS providerStatus ");
    sql.append(" FROM ");
    sql.append("	TBMD_PRDR_M PRDR");
    sql.append(" WHERE ");
    sql.append("	PRDR.ID =:id ");
    Map<String, Object> mapParams = new HashMap<>();
    mapParams.put("id", id);
    try {
      SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
      data = jdbcTemplate.query(sql.toString(), parameters,
          new BeanPropertyRowMapper<>(ProviderDto.class));
      if (CollectionUtils.isNotEmpty(data)) {
        return data.get(0);
      }
    } catch (Exception e) {
      LOGGER.error(logPrefix + "|exception|" + e.getMessage());
    }
    return null;
  }
}
