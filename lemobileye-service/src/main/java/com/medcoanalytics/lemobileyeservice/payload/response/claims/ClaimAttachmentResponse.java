package com.medcoanalytics.lemobileyeservice.payload.response.claims;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ClaimAttachmentResponse {
	
	
	private String claimAttachmentUuid;
    private String fileName;
	private Long fileSize;
	private Instant createdAt;
	private String filePath;
	

}
