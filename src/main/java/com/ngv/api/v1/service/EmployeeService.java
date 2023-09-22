package com.ngv.api.v1.service;

import com.ngv.api.v1.repository.dao.EmployeeDAO;

public interface EmployeeService {
    EmployeeDAO getEmployee(String Department, String StringInput);
}
