package com.pms.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.User;



public interface UserRepo extends JpaRepository<User, Long>{

	
	User  findByEmail(String email);
}
