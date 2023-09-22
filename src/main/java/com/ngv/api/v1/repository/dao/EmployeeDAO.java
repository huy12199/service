package com.ngv.api.v1.repository.dao;

import com.ngv.api.v1.dto.AssetGroupDto;
import com.ngv.api.v1.dto.DeviceDto;
import com.ngv.api.v1.dto.EmployeeDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class EmployeeDAO {
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public List<EmployeeDto> getEmployeeDto( String Department, String StringInput ){ //StringInput Tìm kiếm theo giá trị chuỗi truyền vào trong các giá trị Mã nhân viên, tên nhân viên, email. (theo điều kiện like)

        String logPrefix = "getEmployeeDto";

        LOGGER.info(logPrefix + "|Department|" + Department + "|Department|" + StringInput);
        List<EmployeeDto> data = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("	employeename,  ");
        sql.append("	employeecode, ");
        sql.append("	employeephone, ");
        sql.append("	employeeemail,  ");
        sql.append("	department, ");
        sql.append("	position ");
        sql.append(" FROM ");
        sql.append("	employee ");
        sql.append(" WHERE  ");
        sql.append("	department = :Department ");
        sql.append("	OR employeename LIKE '%':StringInput'%' ");
        sql.append("	OR employeecode LIKE '%':StringInput'%'   ");
        sql.append("	OR employeeemail LIKE '%':StringInput'%'   ");
        Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("Department", Department);
        mapParams.put("Department",StringInput);
        try {
            SqlParameterSource parameters = new MapSqlParameterSource(mapParams);
            data = jdbcTemplate.query(sql.toString(), parameters,
                    new BeanPropertyRowMapper<>(EmployeeDto.class));

        } catch (Exception e) {
            LOGGER.error(logPrefix + "|exception|" + e.getMessage());
        }
        return data;

    }
}
