package com.medcoanalytics.lemobileyeservice.payload.request.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {

	private String email;
	private String passwordResetCode;
	private String newPassword;


}
