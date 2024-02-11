package com.medcoanalytics.lemobileyeservice.service.claims;

import com.medcoanalytics.lemobileyeservice.payload.request.claims.ClaimRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimDetailResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimListResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimLogsResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ClaimService {
	public String createClaim( String personUuid);
	public ResponseEntity<?> updateClaim(String claimUuid, @Valid ClaimRequest claimRequest);
	public ClaimResponse getClaim(String claimUuid);
	public List<ClaimResponse> getClaims();
	public ResponseEntity<?> deleteClaim(String claimUuid);
	public List<ClaimListResponse> getRequestedClaims(String personUuid, String searchKey, int page, int limit);
	public List<ClaimListResponse> getPreparedClaimsByPerson(String personUuid,String searchKey, int page, int limit);
	public List<ClaimListResponse> getApprovedClaimsByInsurance(String personUuid,String searchKey, int page, int limit);
	public List<ClaimLogsResponse> getClaimLogs(String claimUuid, String searchKey, int page, int limit);
	public ClaimDetailResponse getClaimDetails(String claimUuid);




}
