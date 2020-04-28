package com.example.lab2_sw2.Repository;

import com.example.lab2_sw2.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity,String> {

    List<EmployeeEntity> findByFirst_name(String fist);
    List<EmployeeEntity> findByLast_name(String last);


}
