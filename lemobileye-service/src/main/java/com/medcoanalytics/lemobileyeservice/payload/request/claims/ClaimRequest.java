package com.medcoanalytics.lemobileyeservice.payload.request.claims;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
public class ClaimRequest {


	@Size(min = 24, max = 40)
	private String preparedByPersonUuid;

	@Size(min = 24, max = 40)
	private String approvedByInsuranceUuid;

	@Size(min = 24, max = 40)
	private String paidByInsuranceUuid;

	@Size(max = 15)
	private String preparedByPersonStatus;

	@Size(max = 15)
	private String approvedByInsuranceStatus;

	@Size(max = 15)
	private String paidByInsuranceStatus;

	private Date preparedByPersonDate;

	private Date approvedByInsuranceDate;

	private Date paidByInsuranceDate;

	@Size(max = 500)
	private String personComment;

	@Size(max = 500)
	private String insuranceComment;


	private String transactionNumber;


	private Long paymentNumber;


	private Date visitDate;
	


}
