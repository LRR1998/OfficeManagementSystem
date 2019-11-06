package com.ujiuye.pro.service;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.mapper.CustomerMapper;
import com.ujiuye.cust.service.CustomerService;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.mapper.ProjectMapper;
import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceIml implements ProjectService{
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public void saveInfo(Project project) {
        projectMapper.insert(project);
    }

    @Override
    public List<Project> getProjectList() {
        ProjectExample example = new ProjectExample();
        List<Project> projects = projectMapper.selectByExample(example);
        for (Project project:projects) {
            Integer comID = project.getComname();
            Customer customer = customerMapper.selectByPrimaryKey(comID);
            project.setCustomer(customer);
            Integer empFk = project.getEmpFk();
            Employee employee = employeeMapper.selectByPrimaryKey(empFk);
            project.setEmployee(employee);
        }
        return projects;
    }
}
