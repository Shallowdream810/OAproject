package com.ghh.sys.service;

import com.ghh.sys.bean.Employee;

import java.util.List;
import java.util.Map;

public interface IEmployeeService {
    List<Employee> getEmpLeaders();

    Employee loginIn(Employee employee);

    List<Map<String, Object>> findemail(Integer eid);
}
