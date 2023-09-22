package com.ngv.api.v1.service.impl;

import com.ngv.api.v1.dto.EmployeeDto;
import com.ngv.api.v1.repository.dao.EmployeeDAO;
import com.ngv.api.v1.service.EmployeeService;
import com.ngv.base.data.BaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServicelmpl extends BaseService implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Override
    public EmployeeDAO getEmployee(String Department, String StringInput) {
        String logPrefix = "getLimitShopping";
        LOGGER.info(logPrefix + "|Department|" + Department + "|StringInput|" + StringInput);
        employeeDAO.getEmployeeDto(Department, StringInput);
        return null;
    }
}
