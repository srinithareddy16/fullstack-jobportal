package com.jobportal.jobportal_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.jobportal.jobportal_backend.dto.JobPageResponse;
import com.jobportal.jobportal_backend.model.Job;
import com.jobportal.jobportal_backend.repository.JobRepository;


@RestController
@RequestMapping("/api/jobs")
public class JobController {
	
	   @Autowired
	    private JobRepository jobRepository;

	    // CREATE
	    @PostMapping
	    public Job createJob(@RequestBody Job job) {
	        return jobRepository.save(job);
	    }

	    // READ (all)
	    @GetMapping
	    public List<Job> getAllJobs() {
	        return jobRepository.findAll();
	    }

	    // READ (by id)
	    @GetMapping("/{id}")
	    public ResponseEntity<Job> getJobById(@PathVariable Long id) {
	        return jobRepository.findById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    // UPDATE
	    @PutMapping("/{id}")
	    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
	        return jobRepository.findById(id)
	                .map(job -> {
	                    job.setTitle(updatedJob.getTitle());
	                    job.setDescription(updatedJob.getDescription());
	                    job.setCompany(updatedJob.getCompany());
	                    job.setLocation(updatedJob.getLocation());
	                    job.setType(updatedJob.getType());
	                    job.setSalary(updatedJob.getSalary());
	                    return ResponseEntity.ok(jobRepository.save(job));
	                })
	                .orElse(ResponseEntity.notFound().build());
	    }

	    // DELETE
	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
	        if (jobRepository.existsById(id)) {
	            jobRepository.deleteById(id);
	            return ResponseEntity.ok("Job deleted successfully");
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	    @GetMapping("/search")
	    public List<Job> searchJobs(
	            @RequestParam(required = false) String title,
	            @RequestParam(required = false) String location,
	            @RequestParam(required = false) String type
	    ) {
	        return jobRepository.filterJobs(title, location, type);
	    }
	    
	    
	    @GetMapping("/jobs")
	    public JobPageResponse getAllJobs(
	            @RequestParam(defaultValue = "0") int page,
	            @RequestParam(defaultValue = "5") int size,
	            @RequestParam(defaultValue = "id") String sortBy,
	            @RequestParam(defaultValue = "asc") String order
	    ) {
	        Sort sort = order.equalsIgnoreCase("desc")
	            ? Sort.by(sortBy).descending()
	            : Sort.by(sortBy).ascending();

	        Pageable pageable = PageRequest.of(page, size, sort);
	        Page<Job> jobPage = jobRepository.findAll(pageable);

	        return new JobPageResponse(
	            jobPage.getContent(),           // List<Job>
	            jobPage.getNumber(),            // Current page number
	            jobPage.getSize(),              // Page size
	            jobPage.getTotalPages(),        // Total pages
	            jobPage.getTotalElements()      // Total number of jobs
	        );
	    }

	  


}
