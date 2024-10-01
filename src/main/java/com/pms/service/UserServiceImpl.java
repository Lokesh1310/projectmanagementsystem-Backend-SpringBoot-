package com.pms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.Repo.UserRepo;
import com.pms.config.JwtProvider;
import com.pms.model.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public User findUserProfileByJwt(String jwt) throws Exception {

		
		String email=JwtProvider.getEmailFromToken(jwt);
		
		
		
return findUserByEmail(email);
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
  User user=userRepo.findByEmail(email);
  
  if(user==null) {
	  
	  throw new Exception("User Not Found");
  }
  
  

		return user;
	}

	@Override
	public User findUserById(Long userId) throws Exception {

		Optional<User> optionalUser=userRepo.findById(userId);
		
		if(optionalUser.isEmpty()) {
			throw new Exception("user not Found");
		}
		return optionalUser.get();
		
		

}

	@Override
	public User updateUsersProjectSize(User user, int number) {
         
		user.setProjectSize(user.getProjectSize()+number);
		
		return userRepo.save(user);
		

	}

}
