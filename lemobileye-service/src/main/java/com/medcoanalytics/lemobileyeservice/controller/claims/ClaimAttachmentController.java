package com.medcoanalytics.lemobileyeservice.controller.claims;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.medcoanalytics.lemobileyeservice.payload.request.claims.ClaimAttachmentRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimAttachmentResponse;
import com.medcoanalytics.lemobileyeservice.service.claims.ClaimAttachmentService;
import com.medcoanalytics.lemobileyeservice.shared.audit.ValidateRequestBodyList;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lemobileyeservice/claimattachments")
@SecurityRequirement(name = "bearerAuth")

public class ClaimAttachmentController {

	@Autowired
	ClaimAttachmentService claimAttachmentService;

	@PostMapping()
	@JsonProperty("claimAttachments")
	public ResponseEntity<?> createClaimAttachment(@Valid @RequestBody ValidateRequestBodyList<ClaimAttachmentRequest> claimAttachementRequest) throws Exception {
		return claimAttachmentService.createClaimAttachment(claimAttachementRequest.getRequestBody());
	}

	@GetMapping(path="/{claimUuid}")
	public List<ClaimAttachmentResponse> getClaimAttachments(@PathVariable String claimUuid) {
		return claimAttachmentService.getClaimAttachments(claimUuid);
	}

	@DeleteMapping(path="/{claimAttachmentUuid}")
	public ResponseEntity<?> deleteClaimAttachment(@PathVariable String claimAttachmentUuid) {
		return claimAttachmentService.deleteClaimAttachment(claimAttachmentUuid);
	}

	@GetMapping("/open")
	public ResponseEntity<Resource> openAttachment(@RequestParam("claimAttachmentUuid")  String claimAttachmentUuid) throws IOException {
		return claimAttachmentService.openAttachment(claimAttachmentUuid);
	}


	@PutMapping(path="/{claimAttachmentUuid}")
	public ResponseEntity<?> updateClaimAttachment(@PathVariable String claimAttachmentUuid,List<ClaimAttachmentRequest> claimAttachmentRequests) throws IOException {
		return claimAttachmentService.updateClaimAttachment(claimAttachmentRequests);
	}

}
