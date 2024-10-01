package com.pms.request;

import java.time.LocalDate;

import lombok.Data;


@Data
public class IssueRequest {

	
private Long id;
	
	private String  title;
	private String description;
	
	private String status;
	private Long projectId;
	private String priority;
	private LocalDate dueDate;
}
