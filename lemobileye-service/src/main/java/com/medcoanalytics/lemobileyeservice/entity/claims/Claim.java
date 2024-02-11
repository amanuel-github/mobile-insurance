package com.medcoanalytics.lemobileyeservice.entity.claims;

import java.util.Date;
import java.util.UUID;

import com.medcoanalytics.lemobileyeservice.shared.audit.Audit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "claims")
public class Claim  extends Audit {
	/**
	 * 
	 */
	private static final long serialVersionUID = 640850128570066909L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 24, max = 40)
	private String claimUuid = UUID.randomUUID().toString();

	@Size(min = 24, max = 40)
	private String mobileUuid;

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


	@Column(columnDefinition = "boolean default false")
	private boolean isDeleted;
}
