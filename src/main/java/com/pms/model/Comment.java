package com.pms.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Comment {

	

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	   
	
	private String content;
	private LocalDateTime createdDateTime;
	
	@ManyToOne()
	private User user;
	
	@ManyToOne
	private Issue issue;

	
}
