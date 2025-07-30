package com.jobportal.jobportal_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.jobportal_backend.dto.SaveJobRequest;
import com.jobportal.jobportal_backend.model.Job;
import com.jobportal.jobportal_backend.model.SavedJob;
import com.jobportal.jobportal_backend.model.User;
import com.jobportal.jobportal_backend.repository.JobRepository;
import com.jobportal.jobportal_backend.repository.SavedJobRepository;
import com.jobportal.jobportal_backend.repository.UserRepository;

@RestController
@RequestMapping("/api/saved-jobs")
public class SavedJobController {
	
	 @Autowired
	    private SavedJobRepository savedJobRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private JobRepository jobRepository;

	    // ✅ Save a Job
	    @PostMapping("/save")
	    public String saveJob(@RequestBody SaveJobRequest request) {
	        User user = userRepository.findById(request.getUserId())
	                                  .orElseThrow(() -> new RuntimeException("User not found"));

	        Job job = jobRepository.findById(request.getJobId())
	                               .orElseThrow(() -> new RuntimeException("Job not found"));

	        if (savedJobRepository.existsByUserAndJob(user, job)) {
	            return "Job already saved!";
	        }

	        savedJobRepository.save(new SavedJob(user, job));
	        return "Job saved successfully!";
	    }

	    // ✅ Get all saved jobs for a user
	    @GetMapping("/user/{userId}")
	    public List<SavedJob> getSavedJobs(@PathVariable Long userId) {
	        User user = userRepository.findById(userId)
	                                  .orElseThrow(() -> new RuntimeException("User not found"));

	        return savedJobRepository.findByUser(user);
	    }

	    // ✅ Unsave a job
	    @DeleteMapping("/unsave")
	    @Transactional
	    public String unsaveJob(@RequestBody SaveJobRequest request) {
	        User user = userRepository.findById(request.getUserId())
	                                  .orElseThrow(() -> new RuntimeException("User not found"));
	        Job job = jobRepository.findById(request.getJobId())
	                               .orElseThrow(() -> new RuntimeException("Job not found"));

	        savedJobRepository.deleteByUserAndJob(user, job);
	        return "Job unsaved successfully!";
	    }

}
