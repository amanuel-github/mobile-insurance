package com.medcoanalytics.lemobileyeservice.payload.request.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailRequest {

	private String toAddress;
	private String subject;
	private String body;
}
