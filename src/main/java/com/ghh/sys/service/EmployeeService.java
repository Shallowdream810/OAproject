package com.ghh.sys.service;

import com.ghh.sys.bean.Employee;
import com.ghh.sys.bean.EmployeeExample;
import com.ghh.sys.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService implements IEmployeeService {
    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> getEmpLeaders() {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
//        查询pfk为4的员工，即经理
        criteria.andPFkEqualTo(4);
        List<Employee> employees = employeeMapper.selectByExample(example);
        return employees;
    }

    @Override
    public Employee loginIn(Employee employee) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(employee.getUsername());
        criteria.andPasswordEqualTo(employee.getPassword());
        //通过逆向工程获得的是员工列表
        List<Employee> list = employeeMapper.selectByExample(example);
        if (list != null && list.size()>0){
            //判断是否查询出当前员工
            employee = list.get(0);
            return employee;
        }
        return null;
    }

    @Override
    public List<Map<String, Object>> findemail(Integer eid) {
        return employeeMapper.findemail(eid);
    }
}
