package com.pms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.Repo.ChatRepository;
import com.pms.model.Chat;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatRepository chatRepository;
	
	@Override
	public Chat createChat(Chat chat) {

		return chatRepository.save(chat);
		
		
	}

}
