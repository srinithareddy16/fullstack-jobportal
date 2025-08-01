package com.jobportal.jobportal_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.jobportal_backend.model.Job;
import com.jobportal.jobportal_backend.model.User;
import com.jobportal.jobportal_backend.repository.JobRepository;
import com.jobportal.jobportal_backend.repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
	private JobRepository jobRepository;

    // ✅ Get all users
	@PreAuthorize("hasAuthority('admin')")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ Delete user by ID
	@PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully!";
    }

	@PutMapping("/jobs/{id}")
    @PreAuthorize("hasAuthority('admin')")
    public Job updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        Job job = jobRepository.findById(id).orElseThrow();
        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setCompany(updatedJob.getCompany());
        job.setLocation(updatedJob.getLocation());
        job.setType(updatedJob.getType());
        job.setSalary(updatedJob.getSalary());
        return jobRepository.save(job);
    }

}
