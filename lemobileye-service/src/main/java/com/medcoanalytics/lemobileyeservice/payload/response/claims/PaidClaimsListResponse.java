package com.medcoanalytics.lemobileyeservice.payload.response.claims;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaidClaimsListResponse {
	private Long claimNumber;
	private String providerName;
	private String providerTinNumber;
	private String payerName;
	private String checkNumber;
	private String transactionCode;
	private String toBank;
	private String toAccount;
	private Long paymentNumber;
	private Date paymentDate;
	private double totalAmount;
	
}
