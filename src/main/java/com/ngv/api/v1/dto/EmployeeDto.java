package com.ngv.api.v1.dto;

import lombok.Data;

@Data
public class EmployeeDto {
    private String EmployeeNamel; //Tên nhân viên
    private String EmployeeCode;  //Mã nhân viên
    private String EmployeePhone; //Số điện thoại nhân viên
    private String EmployeeEmail; //Email nhân viên
    private String Department;    //Phòng ban
    private String Position;      //Vị trí, chức vụ
}
