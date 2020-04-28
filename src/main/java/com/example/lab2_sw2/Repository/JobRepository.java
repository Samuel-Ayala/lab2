package com.example.lab2_sw2.Repository;

import com.example.lab2_sw2.Entity.JobsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<JobsEntity,Integer> {


}
