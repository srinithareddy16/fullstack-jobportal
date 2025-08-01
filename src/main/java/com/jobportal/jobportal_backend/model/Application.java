package com.jobportal.jobportal_backend.model;



import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    private LocalDateTime appliedAt;

    public Application() {
        this.appliedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
    	return id;
    	}
    public User getUser() {
    	return user; 
    	}
    public void setUser(User user) {
    	this.user = user;
    	}
    public Job getJob() { 
    	return job;
    	}
    public void setJob(Job job) {
    	this.job = job; 
    	}
    public LocalDateTime getAppliedAt() {
    	return appliedAt; 
    	}
    public void setAppliedAt(LocalDateTime appliedAt) {
    	this.appliedAt = appliedAt; 
    	}
}

