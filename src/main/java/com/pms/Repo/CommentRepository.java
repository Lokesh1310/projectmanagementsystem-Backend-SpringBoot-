package com.pms.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	
	List<Comment> findCommmentByIssueId(Long issueId);

	List<Comment> findByIssueId(Long issueId);
	
}
