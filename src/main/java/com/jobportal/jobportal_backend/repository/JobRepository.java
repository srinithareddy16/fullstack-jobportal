package com.jobportal.jobportal_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jobportal.jobportal_backend.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {

	Job save(Job job);

	List<Job> findAll();

	boolean existsById(Long id);

	void deleteById(Long id);

	Optional<Job> findById(Long id);
	
	@Query("SELECT j FROM Job j " +
		       "WHERE (:title IS NULL OR LOWER(j.title) LIKE %:title%) " +
		       "AND (:location IS NULL OR LOWER(j.location) LIKE %:location%) " +
		       "AND (:type IS NULL OR LOWER(j.type) LIKE %:type%)")
		List<Job> filterJobs(@Param("title") String title,
		                     @Param("location") String location,
		                     @Param("type") String type);


	


}
