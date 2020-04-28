package com.example.lab2_sw2.Repository;

import com.example.lab2_sw2.Entity.JobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface JobRepository extends JpaRepository<JobsEntity,String> {
    @Query(value = "SELECT job_title from jobs where ?1 Between min_salary And max_salary", nativeQuery = true)
    List<JobsEntity> EncontrarSalario(int salario);
}
