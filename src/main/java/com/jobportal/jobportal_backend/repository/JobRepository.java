package com.jobportal.jobportal_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobportal.jobportal_backend.model.Job;

public interface JobRepository extends JpaRepository<Job,Long> {

	Job save(Job job);

	List<Job> findAll();

	boolean existsById(Long id);

	void deleteById(Long id);

	Optional<Job> findById(Long id);
	
	@Query("SELECT j FROM Job j WHERE " +
		       "(:title IS NULL OR LOWER(j.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
		       "(:location IS NULL OR LOWER(j.location) LIKE LOWER(CONCAT('%', :location, '%'))) AND " +
		       "(:type IS NULL OR LOWER(j.type) LIKE LOWER(CONCAT('%', :type, '%')))")
		List<Job> searchJobs(
		    @Param("title") String title,
		    @Param("location") String location,
		    @Param("type") String type
		);


	
}
