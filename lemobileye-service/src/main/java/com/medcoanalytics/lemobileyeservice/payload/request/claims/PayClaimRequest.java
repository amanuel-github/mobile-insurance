package com.medcoanalytics.lemobileyeservice.payload.request.claims;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayClaimRequest {
	
	private String claimUuid;
	private String providerUuid;
	private String formBankUuid;
	private String toBankUuid;
	private String checkNumber;
	private String transactionNumber;

}
