package com.pms.service;

import java.util.List;
import java.util.Optional;

import com.pms.model.Issue;
import com.pms.model.User;
import com.pms.request.IssueRequest;

public interface IssueService {

	

 Issue getIssueById(Long issueId) throws Exception;

	List<Issue> getIssueByProjectId (Long projectId) throws Exception;





	void deleteIssue (Long issueId, Long userid) throws Exception;

	
	Issue addUserToIssue (Long issueId, Long userId) throws Exception;

	Issue updateStatus (Long issueId, String status) throws Exception;

	Issue createIssue(IssueRequest issue, User user) throws Exception;
}
