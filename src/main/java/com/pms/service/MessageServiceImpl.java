package com.pms.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.Repo.MessageRepository;
import com.pms.Repo.UserRepo;
import com.pms.model.Chat;
import com.pms.model.Message;
import com.pms.model.User;


@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private MessageRepository messageRepository;
	
	
	@Autowired
	private ProjectService projectService;
	
	
	@Override
	public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
		User sender = userRepo.findById(senderId)
				.orElseThrow (() -> new Exception("User not found with id: "+senderId));
				Chat chat = projectService.getProjectById(projectId).getChat();
				Message message =new Message();
				message.setContent(content);
				message.setSender (sender);
				message.setCreatedAt (LocalDateTime.now());
				message.setChat (chat);
				Message savedMessage=messageRepository.save(message);
				chat.getMessages().add(savedMessage);
				return savedMessage;
	}

	@Override
	public List<Message> getMessagesByProjectId(Long projectId) throws Exception {
		Chat chat= projectService.getChatByProjectId (projectId);
		List<Message> findByChatIdOrderByCreatedAtAsc= messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
		return findByChatIdOrderByCreatedAtAsc;}

}
