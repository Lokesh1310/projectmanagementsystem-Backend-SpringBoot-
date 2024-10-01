package com.pms.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {


	Subscription findByUserId(Long userId);
	
	
}
