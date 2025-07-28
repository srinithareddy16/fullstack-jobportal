package com.jobportal.jobportal_backend.repository;
import com.jobportal.jobportal_backend.model.User;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {

	User findByEmail(String email);


	

}
