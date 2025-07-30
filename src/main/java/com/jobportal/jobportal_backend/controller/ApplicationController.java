package com.jobportal.jobportal_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.jobportal_backend.dto.ApplicationRequest;
import com.jobportal.jobportal_backend.model.Application;
import com.jobportal.jobportal_backend.model.Job;
import com.jobportal.jobportal_backend.model.User;
import com.jobportal.jobportal_backend.repository.ApplicationRepository;
import com.jobportal.jobportal_backend.repository.JobRepository;
import com.jobportal.jobportal_backend.repository.UserRepository;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @PostMapping("/apply")
    public String applyForJob(@RequestBody ApplicationRequest request) {
        User user = userRepository.findById(request.getUserId())
                                  .orElseThrow(() -> new RuntimeException("User not found"));
        Job job = jobRepository.findById(request.getJobId())
                               .orElseThrow(() -> new RuntimeException("Job not found"));

        Application application = new Application();
        application.setUser(user);
        application.setJob(job);

        applicationRepository.save(application);

        return "Application submitted successfully!";
    }
}
