package com.medcoanalytics.lemobileyeservice.payload.response.users;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleResponse {
	  private Integer id;
	  private String roleName;
	  private String roleDescription;
	  private String roleUuid;
	  private List<String> privileges;
	  private long totalPages;



}
