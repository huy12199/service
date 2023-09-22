package com.ngv.api.v1.repository.dao;

import com.ngv.api.v1.dto.ShoppingLimitDto;
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
public class ShopDAO {

  protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
  private final NamedParameterJdbcTemplate jdbcTemplate;

  public ShoppingLimitDto getShoppingLimit(String lastDoY, String year) {
    String logPrefix = "getShoppingLimit";
    LOGGER.info(logPrefix + "|lastDoY|" + lastDoY + "|year|" + year);
    List<ShoppingLimitDto> data = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT * ");
    sql.append("FROM (SELECT DTL_ID budgetGroupId, ");
    sql.append("             DTL_CD budgetGroupCode, ");
    sql.append("             DTL_CD_NM budgetGroupName ");
    sql.append("      FROM TBMD_CDLS_DTL_M ");
    sql.append("      WHERE CD_NM = ");
    sql.append("            'BUD_GRP_CD' ");
    sql.append("        AND rownum = 1) BUD_GRP ");
    sql.append("         LEFT JOIN ");
    sql.append("     (SELECT * ");
    sql.append("      FROM (SELECT BUD.BUD_ID budgetID, ");
    sql.append("                   BUD.BUD_CD budgetCode, ");
    sql.append("                   BUD.BUD_NM budgetName ");
    sql.append("            FROM TBMD_BUD_M BUD ");
    sql.append("            WHERE TRUNC(BUD.VAL_DT) <= TO_DATE(:lastDoY, 'DD/MM/YYYY') ");
    sql.append("            ORDER BY BUD.VAL_DT DESC) a ");
    sql.append("      WHERE rownum = 1) BUD ON 1 = 1 ");
    sql.append("         LEFT join ");
    sql.append("     (SELECT * ");
    sql.append("      FROM (SELECT BUD_ITM.BUD_ITM_ID budgetItemId, ");
    sql.append("                   BUD_ITM.BUD_ITM_CD budgetItemCode, ");
    sql.append("                   BUD_ITM.BUD_ITM_NM budgetItemName");
    sql.append("            FROM TBMD_BUD_ITM_M BUD_ITM ");
    sql.append("            WHERE TRUNC(BUD_ITM.VAL_DT) <= TO_DATE(:lastDoY, 'DD/MM/YYYY') ");
    sql.append("            ORDER BY BUD_ITM.VAL_DT DESC) a ");
    sql.append("      WHERE rownum = 1) BUD_ITM ON 1 = 1 ");
    sql.append("         LEFT join ");
    sql.append("     (SELECT * ");
    sql.append("      FROM (SELECT PUR_BUD.BUD_TOT_AMT totalBudget, ");
    sql.append("                   PUR_BUD.BUD_USE_AMT usedBudget, ");
    sql.append("                   PUR_BUD.BUD_AVAIL_AMT remainBudget ");
    sql.append("            FROM TBPO_PUR_BUD_T PUR_BUD ");
    sql.append("            WHERE FIN_YR = :year ");
    sql.append("              AND TRUNC(PUR_BUD.VAL_DT) <= TO_DATE(:lastDoY, 'DD/MM/YYYY') ");
    sql.append("            ORDER BY PUR_BUD.VAL_DT DESC) a ");
    sql.append("      WHERE rownum = 1) PUR_BUD ON 1 = 1 ");
    Map<String, Object> mapParams = new HashMap<>();
    mapParams.put("lastDoY", lastDoY);
    mapParams.put("year", year);
    try {
      SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
      data = jdbcTemplate.query(sql.toString(), parameters,
          new BeanPropertyRowMapper<>(ShoppingLimitDto.class));
      if (CollectionUtils.isNotEmpty(data)) {
        return data.get(0);
      }
    } catch (Exception e) {
      LOGGER.error(logPrefix + "|exception|" + e.getMessage());
    }
    return null;
  }

}
