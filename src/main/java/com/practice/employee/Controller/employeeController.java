package com.practice.employee.Controller;

import com.practice.employee.Entity.employeeEntity;
import com.practice.employee.Service.employeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class employeeController
{
    @Autowired
    private employeeServiceImpl service;

    @PostMapping("/add")
    public employeeEntity postEmployee(@RequestBody employeeEntity employeeEntity)
    {
        return service.addEmployee(employeeEntity);
    }

    @GetMapping("/")
    public List<employeeEntity> allEmployees()
    {
        return service.getEmployees();
    }

    @GetMapping("/{id}")
    public Optional<employeeEntity> getEmployeeById(@PathVariable Long id)
    {
        return service.getEmployeeById(id);

    }

    @PutMapping("/update/{id}")
    public employeeEntity updateEntity(@PathVariable Long id, @RequestBody employeeEntity employeeEntity)
    {
        return service.updateEmployee(id,employeeEntity);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id)
    {
        return service.deleteEmployee(id);
    }


}
