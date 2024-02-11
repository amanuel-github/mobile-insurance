package com.medcoanalytics.lemobileyeservice.payload.response.claims;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClaimListResponse {

	private String claimUuid;
	private String personComment;
	private String insuranceComment;
	private String transactionNumber;
	private String paymentNumber;
	private String  personUuid;
}
