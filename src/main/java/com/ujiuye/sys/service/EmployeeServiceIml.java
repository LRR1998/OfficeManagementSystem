package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.bean.EmployeeExample;
import com.ujiuye.sys.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceIml implements EmployeeService{

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> getManagers() {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andPFkEqualTo(4);
        List<Employee> employees = employeeMapper.selectByExample(example);
        return employees;
    }

    @Override
    public Employee login(Employee employee) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(employee.getUsername());
        criteria.andPasswordEqualTo(employee.getPassword());
        List<Employee> employees = employeeMapper.selectByExample(example);
        if (employees!=null&&employees.size()>0){
            Employee employee1 = employees.get(0);
            return employee1;
        }
        return null;
    }

    @Override
    public List<Employee> getOthers(Integer eid) {
        List<Employee> list = employeeMapper.getOhers(eid);
        return list;
    }
}
