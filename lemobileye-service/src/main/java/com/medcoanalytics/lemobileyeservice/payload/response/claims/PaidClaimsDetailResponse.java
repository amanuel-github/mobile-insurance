package com.medcoanalytics.lemobileyeservice.payload.response.claims;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PaidClaimsDetailResponse {
	
	private String claimUuid;
	private String mrnNumber;
	private double claimNumber;
	private Date   visitDate;
	private Date   claimDate;
	private String payerName;
	private String payerPhone;
	private String providerName;
	private String providerPhone;
	private String title;
	private String firstName;
	private String fatherName;
	private String grandFatherName;
	private String insuredPhone;
	private String insuranceId;
	private String institutionName;
	private String idNumber;
	
	private String toBank;
	private String toAccount;
	private Long paymentNumber;
	private Date paymentDate;
	private double totalAmount;
	
	private String userTitle;
	private String userFirstName;
	private String userFatherName;
	private String userGrandFatherName;
	private String userPhone;

	
	

}
