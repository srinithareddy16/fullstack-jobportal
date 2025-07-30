package com.jobportal.jobportal_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobportal.jobportal_backend.model.Job;
import com.jobportal.jobportal_backend.model.SavedJob;
import com.jobportal.jobportal_backend.model.User;

public interface SavedJobRepository extends JpaRepository<SavedJob, Long> {
	
	 List<SavedJob> findByUser(User user);

	    boolean existsByUserAndJob(User user, Job job);

	    void deleteByUserAndJob(User user, Job job);

}
