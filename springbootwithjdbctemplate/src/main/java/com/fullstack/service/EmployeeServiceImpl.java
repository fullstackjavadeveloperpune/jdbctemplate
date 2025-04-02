package com.fullstack.service;

import com.fullstack.dao.EmployeeDaoImpl;
import com.fullstack.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeDaoImpl employeeDao;

    public void save(Employee employee) {
        employeeDao.save(employee);
    }

    public Optional<Employee> findById(int empId) {
        return employeeDao.findById(empId);
    }

    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    public void update(int empId, Employee employee) {
        employeeDao.update(empId, employee);

    }

    public void deleteById(int empId) {
        employeeDao.deleteById(empId);
    }
}
