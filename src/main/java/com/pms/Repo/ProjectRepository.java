package com.pms.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pms.model.Project;
import com.pms.model.User;

public interface ProjectRepository extends JpaRepository<Project, Long> {

	
	//List<Project>findByOwner(User user);
	
	List<Project>findBynameContainsAndTeamContains(String partialName,User user );
	
//	
//	@Query("SELECT  p FROM project p  join p.team t where t=:user")
//	List<Project>findProjectByTeam(@Param("user") User user);
//	
	
	List<Project>findByTeamContainingOrOwner(User user,User owner);
	
	
	
	
}
