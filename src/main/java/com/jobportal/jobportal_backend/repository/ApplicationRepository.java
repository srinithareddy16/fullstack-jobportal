package com.jobportal.jobportal_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.jobportal_backend.model.Application;
import com.jobportal.jobportal_backend.model.Job;
import com.jobportal.jobportal_backend.model.User;

public interface ApplicationRepository extends JpaRepository<Application, Long>{
	
	 // Find all applications made by a specific user
    List<Application> findByUser(User user);

    // Find all applications submitted for a specific job
    List<Application> findByJob(Job job);

}
