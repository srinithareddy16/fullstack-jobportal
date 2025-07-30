package com.jobportal.jobportal_backend.dto;

public class SaveJobRequest {
	
	private Long userId;
    private Long jobId;
    
    
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
    
    

}
