package com.pms.service;

import com.pms.model.PlanType;
import com.pms.model.Subscription;
import com.pms.model.User;

public interface SubscriptionService {

	Subscription createSubscriprion(User user);
	
	Subscription getUsersSubscription(Long userId) throws   Exception;
	
	


Subscription upgradeSubscription (Long userId, PlanType planType);

	boolean isValid(Subscription subscription);
}
