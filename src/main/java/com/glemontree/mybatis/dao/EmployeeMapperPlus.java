package com.glemontree.mybatis.dao;

import com.glemontree.mybatis.bean.Employee;

import java.util.List;

public interface EmployeeMapperPlus {
    public Employee getEmpByIdStep(Integer id);
    public Employee getEmpById(Integer id);
    public Employee getEmpAndDept(Integer id);
    public List<Employee> getEmpsByDeptId(Integer deptId);
}