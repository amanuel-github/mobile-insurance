package com.medcoanalytics.lemobileyeservice.controller.claims;


import com.medcoanalytics.lemobileyeservice.payload.request.claims.ClaimRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimDetailResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimListResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimLogsResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimResponse;
import com.medcoanalytics.lemobileyeservice.service.claims.ClaimService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lemobileyeservice/claim")
@SecurityRequirement(name = "bearerAuth")
public class ClaimController {

	@Autowired
	ClaimService claimService;

	@PostMapping("/create-claim/person")
	//@PreAuthorize("hasRole('Create-Claim')")
	public String createClaim(@RequestParam(value = "personUuid") String personUuid) {
		return claimService.createClaim(personUuid);

	}

	@PutMapping(path = "/{claimUuid}")
	@PreAuthorize("hasRole('Update-Claim')")
	public ResponseEntity<?> updateClaim(@PathVariable String claimUuid, @Valid @RequestBody ClaimRequest claimRequest) {
		return claimService.updateClaim(claimUuid, claimRequest);
	}

	@GetMapping(path = "/{claimUuid}")
	@PreAuthorize("hasRole('Read-Claim')")
	public ClaimResponse getClaim(@PathVariable String claimUuid) {
		return claimService.getClaim(claimUuid);
	}


	@DeleteMapping(path = "/{claimUuid}")
	@PreAuthorize("hasRole('Delete-Claim')")
	public ResponseEntity<?> deleteClaim(@PathVariable String claimUuid) {
		return claimService.deleteClaim(claimUuid);
	}


	@GetMapping("/requested/list/{personUuid}")
	@PreAuthorize("hasRole('Read-Requested-Claims-Person')")
	public List<ClaimListResponse> getRequestedClaims(@PathVariable String personUuid, @RequestParam(name = "search", required = false) String searchKey,
													  @RequestParam(value = "page", defaultValue = "1") int page,
													  @RequestParam(value = "limit", defaultValue = "25") int limit) {
		return claimService.getRequestedClaims(personUuid,searchKey, page, limit);
	}

	@GetMapping("/prepared/lis/{personUuid}")
	//@PreAuthorize("hasRole('Read-Reviewed-Claims')")
	public List<ClaimListResponse> getPreParedClaimsByPerson(@PathVariable String personUuid,
			@RequestParam(name = "search", required = false) String searchKey,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {
		return claimService.getPreparedClaimsByPerson(personUuid,searchKey, page, limit);
	}


	@GetMapping("/approved/list/{personUuid}")
	@PreAuthorize("hasRole('Read-Approved-Claims-Insurance')")
	public List<ClaimListResponse> getApprovedClaimsByInsurance(@PathVariable String personUuid,
			@RequestParam(name = "search", required = false) String searchKey,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {
		return claimService.getApprovedClaimsByInsurance(personUuid,searchKey, page, limit);
	}

	@GetMapping("/logs")
	@PreAuthorize("hasRole('Read-Claim-Logs')")
	public List<ClaimLogsResponse> getClaimLogs(@RequestParam("claimUuid") String claimUuid,
												@RequestParam(name = "search", required = false) String searchKey,
												@RequestParam(value = "page", defaultValue = "1") int page,
												@RequestParam(value = "limit", defaultValue = "25") int limit) {
		return claimService.getClaimLogs(claimUuid, searchKey, page, limit);
	}

	@GetMapping(path = "/details/{claimUuid}")
	// @PreAuthorize("hasRole('Read-Claim')")
	public ClaimDetailResponse getClaimDetails(@PathVariable String claimUuid) {
		return claimService.getClaimDetails(claimUuid);
	}

}