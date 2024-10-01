package com.pms.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.model.Issue;

public interface IssueRepository extends JpaRepository<Issue, Long> {

	public List<Issue> findByProjectID(Long id);
	
}
