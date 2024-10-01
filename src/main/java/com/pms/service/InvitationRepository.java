package com.pms.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Long>{

	
	Invitation findByToken(String token);
	
	Invitation findByEmail(String userEmail);
	
}
