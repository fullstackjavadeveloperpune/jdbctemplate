package com.fullstack.dao;

import com.fullstack.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class EmployeeDaoImpl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Employee employee(ResultSet resultSet, int n) throws SQLException {
        return Employee.builder().empId(resultSet.getInt(1)).empName(resultSet.getString(2)).empAddress(resultSet.getString(3)).empSalary(resultSet.getDouble(4)).empContactNumber(resultSet.getLong(5)).empDOB(resultSet.getDate(6)).build();
    }

    String insertSQL = "insert into employee(empid, empname, empaddress, empsalary, empcontactnumber, empdob)values(?, ?, ?, ?, ?, ?)";

    String selectByIdSQL = "select * from employee where empid=?";

    String selectAllSQL = "select * from employee";

    String updateSQL = "update employee set empname=?, empaddress=?, empsalary=?, empcontactnumber=?, empdob=? where empid=?";

    String deleteSQL = "delete from employee where empid=?";

    public void save(Employee employee) {
        jdbcTemplate.update(insertSQL, employee.getEmpId(), employee.getEmpName(), employee.getEmpAddress(), employee.getEmpSalary(), employee.getEmpContactNumber(), employee.getEmpDOB());
    }

    public Optional<Employee> findById(int empId) {
        List<Employee> employees = jdbcTemplate.query(selectByIdSQL, this::employee, empId);
        return employees.isEmpty() ? Optional.empty() : Optional.of(employees.get(0));
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query(selectAllSQL, this::employee);
    }

    public void update(int empId, Employee employee) {
        jdbcTemplate.update(updateSQL, employee.getEmpName(), employee.getEmpAddress(), employee.getEmpSalary(), employee.getEmpContactNumber(), employee.getEmpDOB(), empId);
    }

    public void deleteById(int empId) {
        jdbcTemplate.update(deleteSQL, empId);
    }


}
