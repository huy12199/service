package com.ngv.api.v1.repository.dao;

import com.ngv.api.v1.dto.AssetDto;
import com.ngv.api.v1.dto.AssetGroupDto;
import com.ngv.api.v1.dto.AssetStatusDto;
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
public class AssetDAO {

  protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  private final NamedParameterJdbcTemplate jdbcTemplate;

  public List<AssetGroupDto> getAssetGroup(String fromDate) {
    String logPrefix = "getAssetGroup";
    LOGGER.info(logPrefix + "|fromDate|" + fromDate);
    List<AssetGroupDto> data = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT ");
    sql.append("	UOM.DTL_ID assetGroupId, ");
    sql.append("	UOM.DTL_CD assetGroupCode, ");
    sql.append("	UOM.DTL_CD_NM assetGroupName, ");
    sql.append("	UOM.INPUT_DT updateDate, ");
    sql.append("	CASE WHEN UOM.IS_ACTIVE =1 THEN 'ACTIVE' ELSE 'INAVTIVE' END  assetGroupStatus ");
    sql.append("FROM TBMD_CDLS_DTL_M  UOM ");
    sql.append("WHERE UOM.CD_NM= 'AST_GRP_CD'  ");
    sql.append("	AND TRUNC(UOM.INPUT_DT)  >=TO_DATE(:fromDate, 'DD/MM/YYYY') ");
    Map<String, Object> mapParams = new HashMap<>();
    mapParams.put("fromDate", fromDate);
    try {
      SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
      data = jdbcTemplate.query(sql.toString(), parameters,
          new BeanPropertyRowMapper<>(AssetGroupDto.class));
    } catch (Exception e) {
      LOGGER.error(logPrefix + "|exception|" + e.getMessage());
    }
    return data;
  }

  public List<AssetGroupDto> getAssetGroupByDtlIdAndCdId(String dtlId, String cdId) {
    String logPrefix = "getAssetGroupByDtlIdAndCdId";
    LOGGER.info(logPrefix + "|dtlId|" + dtlId + "|cdId|" + cdId);
    List<AssetGroupDto> data = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT ");
    sql.append("	UOM.DTL_ID assetGroupId, ");
    sql.append("	UOM.DTL_CD assetGroupCode, ");
    sql.append("	UOM.DTL_CD_NM assetGroupName, ");
    sql.append("	UOM.INPUT_DT updateDate, ");
    sql.append("	CASE WHEN UOM.IS_ACTIVE =1 THEN 'ACTIVE' ELSE 'INAVTIVE' END  assetGroupStatus ");
    sql.append("FROM TBMD_CDLS_DTL_M  UOM ");
    sql.append("WHERE UOM.CD_NM= 'AST_GRP_CD'  ");
    sql.append("	AND UOM.DTL_ID =:dtlId AND CD_ID=:cdId ");
    Map<String, Object> mapParams = new HashMap<>();
    mapParams.put("dtlId", dtlId);
    mapParams.put("cdId", cdId);
    try {
      SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
      data = jdbcTemplate.query(sql.toString(), parameters,
          new BeanPropertyRowMapper<>(AssetGroupDto.class));
    } catch (Exception e) {
      LOGGER.error(logPrefix + "|exception|" + e.getMessage());
    }
    return data;
  }


  public AssetDto getAssetDetail(String fromDate) {
    String logPrefix = "getAssetDetail";
    LOGGER.info(logPrefix + "|fromDate|" + fromDate);
    List<AssetDto> data = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT a.* FROM ( SELECT  ");
    sql.append("	GPRPTY.DTL_ID	assetGroupID, ");
    sql.append("	GPRPTY.DTL_CD	assetGroupCode, ");
    sql.append("	GPRPTY.DTL_CD_NM	assetGroupName, ");
    sql.append("	GEQP.EQP_GRP_ID	equipmentGroupiD, ");
    sql.append("	GEQP.EQP_GRP_CD	equipmentGroupCode, ");
    sql.append("	GEQP.EQP_GRP_NM	equipmentGroupName, ");
    sql.append("	AST.AST_ID	assetID, ");
    sql.append("	AST.AST_CD	assetCode, ");
    sql.append("	AST. MODL	model, ");
    sql.append("	AST. SRS	seri, ");
    sql.append("	AST.UNIT	unit, ");
    sql.append("	AST.QTY	quantity, ");
    sql.append("	AST.AST_STS	status, ");
    sql.append("	AST.NOTE	reason, ");
    sql.append("	AST.INPUT_DT	updateDate ");
    sql.append("FROM TBPO_AST_T  AST LEFT JOIN  ");
    sql.append("	TBMD_CDLS_DTL_M  GPRPTY ON AST.AST_GRP_ID= GPRPTY. DTL_ID ");
    sql.append("AND GPRPTY.CD_NM= 'PRPTY_GRP_CD' ");
    sql.append("LEFT JOIN  ");
    sql.append(
        "(SELECT A.* FROM (SELECT * FROM TBMD_EQP_GRP_M ORDER BY INPUT_DT)A WHERE rownum =1) GEQP  ");
    sql.append("	ON AST.EQP_GRP_ID= GEQP.EQP_GRP_ID  ");
    sql.append("	AND TRUNC(AST.INPUT_DT)  >=TO_DATE(:fromDate, 'DD/MM/YYYY') ");
    sql.append("ORDER BY AST.INPUT_DT DESC )a WHERE rownum =1 ");
    Map<String, Object> mapParams = new HashMap<>();
    mapParams.put("fromDate", fromDate);
    try {
      SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
      data = jdbcTemplate.query(sql.toString(), parameters,
          new BeanPropertyRowMapper<>(AssetDto.class));
      if (CollectionUtils.isNotEmpty(data)) {
        return data.get(0);
      }
    } catch (Exception e) {
      LOGGER.error(logPrefix + "|exception|" + e.getMessage());
    }
    return null;
  }

  public AssetDto getAssetById(Long id) {
    String logPrefix = "getAssetById";
    LOGGER.info(logPrefix + "|id|" + id);
    List<AssetDto> data = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT a.* FROM ( SELECT  ");
    sql.append("	GPRPTY.DTL_ID	assetGroupID, ");
    sql.append("	GPRPTY.DTL_CD	assetGroupCode, ");
    sql.append("	GPRPTY.DTL_CD_NM	assetGroupName, ");
    sql.append("	GEQP.EQP_GRP_ID	equipmentGroupiD, ");
    sql.append("	GEQP.EQP_GRP_CD	equipmentGroupCode, ");
    sql.append("	GEQP.EQP_GRP_NM	equipmentGroupName, ");
    sql.append("	AST.AST_ID	assetID, ");
    sql.append("	AST.AST_CD	assetCode, ");
    sql.append("	AST. MODL	model, ");
    sql.append("	AST. SRS	seri, ");
    sql.append("	AST.UNIT	unit, ");
    sql.append("	AST.QTY	quantity, ");
    sql.append("	AST.AST_STS	status, ");
    sql.append("	AST.NOTE	reason, ");
    sql.append("	AST.INPUT_DT	updateDate ");
    sql.append("FROM TBPO_AST_T  AST LEFT JOIN  ");
    sql.append("	TBMD_CDLS_DTL_M  GPRPTY ON AST.AST_GRP_ID= GPRPTY. DTL_ID ");
    sql.append("AND GPRPTY.CD_NM= 'PRPTY_GRP_CD' ");
    sql.append("LEFT JOIN  ");
    sql.append(
        "(SELECT A.* FROM (SELECT * FROM TBMD_EQP_GRP_M ORDER BY INPUT_DT)A WHERE rownum =1) GEQP  ");
    sql.append("	ON AST.EQP_GRP_ID= GEQP.EQP_GRP_ID  ");
    sql.append("	AND AST.ID =:id ");
    sql.append("ORDER BY AST.INPUT_DT DESC )a WHERE rownum =1 ");
    Map<String, Object> mapParams = new HashMap<>();
    mapParams.put("id", id);
    try {
      SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
      data = jdbcTemplate.query(sql.toString(), parameters,
          new BeanPropertyRowMapper<>(AssetDto.class));
      if (CollectionUtils.isNotEmpty(data)) {
        return data.get(0);
      }
    } catch (Exception e) {
      LOGGER.error(logPrefix + "|exception|" + e.getMessage());
    }

    return null;
  }

  public AssetStatusDto getStatusAsset(String fromDate) {
    String logPrefix = "getStatusAsset";
    LOGGER.info(logPrefix + "|fromDate|" + fromDate);
    List<AssetStatusDto> data = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT a.* FROM( SELECT  ");
    sql.append("	AST.AST_ID, assetId ");
    sql.append("	AST.AST_CD, assetCode ");
    sql.append("	AST.AST_STS, status ");
    sql.append("	AST.NOTE, reason ");
    sql.append("	AST.INPUT_DT updateDate ");
    sql.append("FROM TBPO_AST_T AST  ");
    sql.append(
        " WHERE TRUNC(AST.INPUT_DT)  >=TO_DATE(:fromDate, 'DD/MM/YYYY') ORDER BY AST.INPUT_DT )a WHERE rownum =1 ");
    Map<String, Object> mapParams = new HashMap<>();
    mapParams.put("fromDate", fromDate);
    try {
      SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
      data = jdbcTemplate.query(sql.toString(), parameters,
          new BeanPropertyRowMapper<>(AssetStatusDto.class));
      if (CollectionUtils.isNotEmpty(data)) {
        return data.get(0);
      }
    } catch (Exception e) {
      LOGGER.error(logPrefix + "|exception|" + e.getMessage());
    }
    return null;
  }
}
