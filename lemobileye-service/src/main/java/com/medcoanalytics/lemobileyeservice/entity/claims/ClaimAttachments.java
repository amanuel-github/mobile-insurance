package com.medcoanalytics.lemobileyeservice.entity.claims;

import java.util.UUID;

import com.medcoanalytics.lemobileyeservice.shared.audit.Audit;
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


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "claim_attachments")
public class ClaimAttachments extends Audit {

	private static final long serialVersionUID = 4221081290836121794L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 36, max = 40)
	private String claimAttachmentUuid = UUID.randomUUID().toString();
	
	@GeneratedValue(strategy = GenerationType.UUID)
	private String claimUuid;
	
	private String file;

	private String fileName;

	private Long fileSize;
	
}
