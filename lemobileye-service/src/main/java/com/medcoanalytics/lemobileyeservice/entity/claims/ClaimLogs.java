package com.medcoanalytics.lemobileyeservice.entity.claims;

import java.time.Instant;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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
public class ClaimLogs  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 36, max = 40)
	private String claimUuid;
	
	@Size(min = 36, max = 40)
	private String actionByUuid;

	@NotBlank
	@Size(min = 2, max = 500)
	private String comment;
	
	private Instant actionDate;
    
	private String actionStatus;
	private String previousStatus;
}
