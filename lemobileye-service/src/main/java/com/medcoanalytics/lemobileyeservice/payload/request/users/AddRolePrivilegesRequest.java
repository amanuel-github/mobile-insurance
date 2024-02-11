package com.medcoanalytics.lemobileyeservice.payload.request.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddRolePrivilegesRequest {
	  private String [] privileges;
}
