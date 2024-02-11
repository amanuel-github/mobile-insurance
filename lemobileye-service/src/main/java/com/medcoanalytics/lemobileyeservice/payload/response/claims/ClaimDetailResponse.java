package com.medcoanalytics.lemobileyeservice.payload.response.claims;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClaimDetailResponse {
	private String claimUuid;
	private String insuredPersonUuid;
	private String payerInstitutionContractCode;
	private String payerInstitutionContractName;
	private double remainingAmount;
	private String mrnNumber;
	private double claimNumber;
	private Date   visitDate;
	private String payerName;
	private String payerPhone;
	private String title;
	private String firstName;
	private String fatherName;
	private String grandFatherName;
	private String insuredPhone;
	private String insuranceId;
	private String institutionName;
	private String idNumber;
	
	private String providerName;
	private String providerPhone;
	private String providerTinNumber;

}
