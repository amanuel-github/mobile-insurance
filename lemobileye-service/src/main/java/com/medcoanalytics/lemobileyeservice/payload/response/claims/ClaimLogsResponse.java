package com.medcoanalytics.lemobileyeservice.payload.response.claims;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClaimLogsResponse {
	

	private Date  actionDate;
	private String phone;
	private String title;
	private String firstName;
	private String fatherName;
	private String grandFatherName;
	private String comment;
	private String status;
	private String previousStatus;
	private long totalPages;
	
}
