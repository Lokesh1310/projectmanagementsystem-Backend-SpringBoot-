package com.pms.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {

	
	
}
