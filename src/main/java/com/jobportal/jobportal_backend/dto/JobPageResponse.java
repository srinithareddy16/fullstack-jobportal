package com.jobportal.jobportal_backend.dto;

import java.util.List;

import com.jobportal.jobportal_backend.model.Job;

public class JobPageResponse {
	 private List<Job> content;
	    private int page;
	    private int size;
	    private int totalPages;
	    private long totalElements;
	    
	    public JobPageResponse(List<Job> content, int page, int size, int totalPages, long totalElements) {
	        this.content = content;
	        this.page = page;
	        this.size = size;
	        this.totalPages = totalPages;
	        this.totalElements = totalElements;
	    }

		public List<Job> getContent() {
			return content;
		}

		public void setContent(List<Job> content) {
			this.content = content;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public int getTotalPages() {
			return totalPages;
		}

		public void setTotalPages(int totalPages) {
			this.totalPages = totalPages;
		}

		public long getTotalElements() {
			return totalElements;
		}

		public void setTotalElements(long totalElements) {
			this.totalElements = totalElements;
		}
	    
	    

}
