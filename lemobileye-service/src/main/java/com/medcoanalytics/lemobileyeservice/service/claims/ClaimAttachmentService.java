package com.medcoanalytics.lemobileyeservice.service.claims;

import com.medcoanalytics.lemobileyeservice.payload.request.claims.ClaimAttachmentRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimAttachmentResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;


public interface ClaimAttachmentService {
	public List<ClaimAttachmentResponse> getClaimAttachments(String claimUuid);

	public ResponseEntity<?> deleteClaimAttachment(String claimAttachmentUuid);

	public ResponseEntity<?> updateClaimAttachment(List<ClaimAttachmentRequest> claimAttachmentRequests) throws IOException;

	public ResponseEntity<Resource> openAttachment(String claimAttachmentUuid) throws IOException;

	ResponseEntity<?> createClaimAttachment(List<ClaimAttachmentRequest> claimAttachmentRequests) throws IOException;

}
