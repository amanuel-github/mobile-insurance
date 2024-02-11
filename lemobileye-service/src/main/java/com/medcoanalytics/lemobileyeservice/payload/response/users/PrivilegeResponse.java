package com.medcoanalytics.lemobileyeservice.payload.response.users;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PrivilegeResponse {
	 
	  private String privilegeName;
	  private String privilegeDescription;
	  private String privilegeCategory;
	  private String privilegeUuid;
	  private long totalPages;



}
