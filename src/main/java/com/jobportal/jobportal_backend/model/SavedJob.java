package com.jobportal.jobportal_backend.model;

import jakarta.persistence.*;


@Entity
public class SavedJob {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Job job;

    // Constructors
    public SavedJob() {}
    public SavedJob(User user, Job job) {
        this.user = user;
        this.job = job;
    }
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
    
    

}
