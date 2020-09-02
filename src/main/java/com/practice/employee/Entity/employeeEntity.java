package com.practice.employee.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class employeeEntity
{
    @Id
    private Long id;
    private String empLName;
    private String empFName;
    private String empDept;
    private String empAge;


}
