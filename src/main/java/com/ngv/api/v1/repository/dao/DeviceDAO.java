package com.ngv.api.v1.repository.dao;

import com.ngv.api.v1.dto.DeviceDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class DeviceDAO {

  protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  private final NamedParameterJdbcTemplate jdbcTemplate;

  public List<DeviceDto> getDeviceGroup(String fromDate) {
    String logPrefix = "getDeviceGroup";
    LOGGER.info(logPrefix + "|fromDate|" + fromDate);
    List<DeviceDto> data = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT ");
    sql.append("	GEQP.ID equipmentGroupId, ");
    sql.append("	UOM.DTL_ID assetGroupId, ");
    sql.append("	UOM.DTL_CD assetGroupCode, ");
    sql.append("	GEQP.EQP_GRP_CD equipmentGroupCode, ");
    sql.append("	GEQP. EQP_GRP_NM equipmentGroupName, ");
    sql.append("	GEQP.INPUT_DT dataDate ");
    sql.append(" FROM ");
    sql.append("	TBMD_EQP_GRP_M GEQP ");
    sql.append(" INNER JOIN TBMD_CDLS_DTL_M UOM ON ");
    sql.append("	GEQP.ID = UOM.DTL_ID ");
    sql.append("	AND UOM.CD_NM = 'AST_GRP_CD' ");
    sql.append("	AND TRUNC(GEQP.INPUT_DT) >= TO_DATE(:fromDate, 'DD/MM/YYYY') ");
    Map<String, Object> mapParams = new HashMap<>();
    mapParams.put("fromDate", fromDate);
    try {
      SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
      data = jdbcTemplate.query(sql.toString(), parameters,
          new BeanPropertyRowMapper<>(DeviceDto.class));

    } catch (Exception e) {
      LOGGER.error(logPrefix + "|exception|" + e.getMessage());
    }
    return data;
  }

  public List<DeviceDto> getDeviceGroupById(Long id) {
    String logPrefix = "getDeviceGroupById";
    LOGGER.info(logPrefix + "|id|" + id);
    List<DeviceDto> data = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT ");
    sql.append("	GEQP.ID equipmentGroupId, ");
    sql.append("	UOM.DTL_ID assetGroupId, ");
    sql.append("	UOM.DTL_CD assetGroupCode, ");
    sql.append("	GEQP.EQP_GRP_CD equipmentGroupCode, ");
    sql.append("	GEQP. EQP_GRP_NM equipmentGroupName, ");
    sql.append("	GEQP.INPUT_DT dataDate ");
    sql.append(" FROM ");
    sql.append("	TBMD_EQP_GRP_M GEQP ");
    sql.append(" INNER JOIN TBMD_CDLS_DTL_M UOM ON ");
    sql.append("	GEQP.ID = UOM.DTL_ID ");
    sql.append("	AND UOM.CD_NM = 'AST_GRP_CD' ");
    sql.append("	AND GEQP.ID =:id ");
    Map<String, Object> mapParams = new HashMap<>();
    mapParams.put("id", id);
    try {
      SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
      data = jdbcTemplate.query(sql.toString(), parameters,
          new BeanPropertyRowMapper<>(DeviceDto.class));
    } catch (Exception e) {
      LOGGER.error(logPrefix + "|exception|" + e.getMessage());
    }
    return data;
  }

}
