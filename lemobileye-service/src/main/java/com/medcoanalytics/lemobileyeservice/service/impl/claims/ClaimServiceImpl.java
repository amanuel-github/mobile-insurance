package com.medcoanalytics.lemobileyeservice.service.impl.claims;

import com.medcoanalytics.lemobileyeservice.entity.claims.Claim;
import com.medcoanalytics.lemobileyeservice.exception.ResourceNotFoundException;
import com.medcoanalytics.lemobileyeservice.payload.request.claims.ClaimRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.MessageResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimDetailResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimListResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimLogsResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimResponse;
import com.medcoanalytics.lemobileyeservice.repository.claims.ClaimListRepository;
import com.medcoanalytics.lemobileyeservice.repository.claims.ClaimLogsRepository;
import com.medcoanalytics.lemobileyeservice.repository.claims.ClaimRepository;
import com.medcoanalytics.lemobileyeservice.service.claims.ClaimService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClaimServiceImpl implements ClaimService {
	@Autowired
	ClaimRepository claimRepository;

	@Autowired
	ClaimListRepository claimListRepository;


	@Autowired
	ClaimLogsRepository claimLogsRepository;


	@Override
	public String createClaim(String personUuid)
	{
		return null;
	}

	@Override
	public ResponseEntity<?> updateClaim(String claimUuid, ClaimRequest claimRequest) {
		Claim claim = claimRepository.findByClaimUuid(claimUuid);
		if (claim == null)
			throw  new ResourceNotFoundException("Claim", "claimUuid", claimUuid);
		BeanUtils.copyProperties(claimRequest, claim);
		claimRepository.save(claim);
		return ResponseEntity.ok(new MessageResponse("Claim updated Successfully!"));

	}

	@Override
	public ClaimResponse getClaim(String claimUuid) {
		Claim claim = claimRepository.findByClaimUuid(claimUuid);
		ClaimResponse claimResponse = new ClaimResponse();
		BeanUtils.copyProperties(claim, claimResponse);
		return claimResponse;

	}

	@Override
	public List<ClaimResponse> getClaims() {
		return null;
	}

	@Override
	public ResponseEntity<?> deleteClaim(String claimUuid) {
		Claim claim = claimRepository.findByClaimUuid(claimUuid);
		if (claim == null)
			throw  new ResourceNotFoundException("Claim", "claimUuid", claimUuid);
		claim.setDeleted(true);
		claimRepository.save(claim);
		return ResponseEntity.ok(new MessageResponse("Claim deleted successfully!"));

	}

	@Override
	public List<ClaimListResponse> getRequestedClaims(String personUuid , String searchKey, int page, int limit) {



		return claimListRepository.findAllRequestedClaims(personUuid, searchKey, page, limit);

	}

	@Override
	public List<ClaimListResponse> getPreparedClaimsByPerson(String personUuid,String searchKey, int page, int limit) {
		return claimListRepository.findAllPreparedClaimsByPerson(personUuid, searchKey, page, limit);
	}

	@Override
	public List<ClaimListResponse> getApprovedClaimsByInsurance(String personUuid,String searchKey, int page, int limit) {
		return claimListRepository.findAllApprovedClaimsByInsurance(personUuid, searchKey, page, limit);
	}


	@Override
	public List<ClaimLogsResponse> getClaimLogs(String claimUuid, String searchKey, int page, int limit) {
		return claimListRepository.findClaimLogs(claimUuid, searchKey, page, limit);
	}

	@Override
	public ClaimDetailResponse getClaimDetails(String claimUuid) {
		return claimListRepository.findClaimDetails(claimUuid);
	}

}
