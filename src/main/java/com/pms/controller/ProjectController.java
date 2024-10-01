package com.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pms.model.Chat;
import com.pms.model.Invitation;
import com.pms.model.Project;
import com.pms.model.User;
import com.pms.reponse.MessageResponse;
import com.pms.request.InviteRequest;
import com.pms.service.InvitationService;
import com.pms.service.ProjectService;
import com.pms.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@Autowired
	private UserService userService;

	@Autowired
	private InvitationService invitationService;

	@GetMapping
	public ResponseEntity<List<Project>> getProjects(@RequestParam(required = false) String category,
			@RequestParam(required = false) String tag, @RequestHeader("Authorization") String jwt

	) throws Exception {

		User user = userService.findUserProfileByJwt(jwt);
		List<Project> projects = projectService.getProjectByTeam(user, category, tag);

		return new ResponseEntity<>(projects, HttpStatus.OK);
	}

	@GetMapping("/{projectId}")
	public ResponseEntity<Project> getProjectById(@PathVariable Long projectId,
			@RequestHeader("Authorization") String jwt

	) throws Exception {

		User user = userService.findUserProfileByJwt(jwt);
		Project project = projectService.getProjectById(projectId);

		return new ResponseEntity<>(project, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Project> createProject(

			@RequestHeader("Authorization") String jwt, @RequestBody Project project

	) throws Exception {

		User user = userService.findUserProfileByJwt(jwt);
		Project createdproject = projectService.createProject(project, user);

		return new ResponseEntity<>(createdproject, HttpStatus.OK);
	}

	@PatchMapping("/{projectId}")
	public ResponseEntity<Project> updateProject(@PathVariable Long projectId,
			@RequestHeader("Authorization") String jwt, @RequestBody Project project

	) throws Exception {

		User user = userService.findUserProfileByJwt(jwt);
		Project updatedproject = projectService.updateProject(project, projectId);

		return new ResponseEntity<>(updatedproject, HttpStatus.OK);
	}

	@DeleteMapping("/{projectId}")
	public ResponseEntity<MessageResponse> deleteProject(@PathVariable Long projectId,
			@RequestHeader("Authorization") String jwt

	) throws Exception {

		User user = userService.findUserProfileByJwt(jwt);
		projectService.deleteProject(projectId, user.getId());

		MessageResponse res = new MessageResponse("project deleted successfully");

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<List<Project>> searchProjects(@RequestParam(required = false) String keyword,

			@RequestHeader("Authorization") String jwt

	) throws Exception {

		User user = userService.findUserProfileByJwt(jwt);
		List<Project> projects = projectService.searchProjects(keyword, user);

		return new ResponseEntity<>(projects, HttpStatus.OK);
	}

	@GetMapping("/{projectId}/chat")
	public ResponseEntity<Chat> getChatByProjectById(@PathVariable Long projectId,
			@RequestHeader("Authorization") String jwt

	) throws Exception {

		User user = userService.findUserProfileByJwt(jwt);
		Chat chat = projectService.getChatByProjectId(projectId);

		return new ResponseEntity<>(chat, HttpStatus.OK);
	}

	@PostMapping("/invite")
	public ResponseEntity<MessageResponse> inviteProject(
			@RequestBody InviteRequest inviteRequest,
			@RequestHeader("Authorization") String jwt

	) throws Exception {

		User user = userService.findUserProfileByJwt(jwt);

		invitationService.sendInvitation(inviteRequest.getEmail(), inviteRequest.getProjectId());

		MessageResponse response = new MessageResponse("User Invitation send");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/accept_invitation")
	public ResponseEntity<Invitation> acceptInviteProject(

			@RequestParam String token,
			@RequestHeader("Authorization") String jwt

	) throws Exception {

		User user = userService.findUserProfileByJwt(jwt);

		Invitation invitation = invitationService.acceptInvitation(token, user.getId());

		projectService.addUserToProject(invitation.getProjectId(), user.getId());

		return new ResponseEntity<>(invitation, HttpStatus.ACCEPTED);
	}

}
