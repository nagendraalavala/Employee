package com.practice.employee.Service;

import com.practice.employee.Entity.employeeEntity;
import com.practice.employee.Repository.employeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class employeeServiceImpl {

    @Autowired
    private employeeRepo repo;


    public employeeEntity addEmployee(employeeEntity entity)
    {
        return  (repo.save(entity));

    }

    public List<employeeEntity> getEmployees() {
        return (repo.findAll());
    }

    public Optional<employeeEntity> getEmployeeById(Long id) {
        Optional<employeeEntity> foundEmployee = repo.findById(id);
        return foundEmployee;
    }

    public employeeEntity updateEmployee(Long id, employeeEntity newEntity) {
        Optional<employeeEntity> getEmployee = repo.findById(id);
        employeeEntity entity = getEmployee.get();
        if(getEmployee.isPresent())
        {
            entity.setEmpLName(newEntity.getEmpLName());
            entity.setEmpFName(newEntity.getEmpFName());
            entity.setEmpAge(newEntity.getEmpAge());
            entity.setEmpDept(newEntity.getEmpDept());
            repo.save(entity);

        }
        return entity;

    }

    public String deleteEmployee(Long id) {
        Optional<employeeEntity> getEmployee = repo.findById(id);
        if(getEmployee.isPresent())
       {
           repo.deleteById(id);
       }
       return "Employee Deleted";
    }
}
