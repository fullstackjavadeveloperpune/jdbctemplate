package com.fullstack.controller;

import com.fullstack.exception.RecordNotFoundException;
import com.fullstack.model.Employee;
import com.fullstack.service.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Employee employee) {
        employeeService.save(employee);
        return ResponseEntity.ok("Data Saved Successfully");
    }

    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable int empId) {
        return ResponseEntity.ok(employeeService.findById(empId));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<String> update(@PathVariable int empId, @RequestBody Employee employee) {
        Employee employee1 = employeeService.findById(empId).orElseThrow(() -> new RecordNotFoundException("Employee #ID Does Not Exist"));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpDOB(employee.getEmpDOB());

        employeeService.update(empId, employee);

        return ResponseEntity.ok("Data Updated Successfully");
    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> delteById(@PathVariable int empId) {
        employeeService.deleteById(empId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }
}
