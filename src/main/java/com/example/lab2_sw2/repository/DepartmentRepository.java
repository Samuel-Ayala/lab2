package com.example.lab2_sw2.repository;

import com.example.lab2_sw2.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository <Departments, Integer>  {

}
