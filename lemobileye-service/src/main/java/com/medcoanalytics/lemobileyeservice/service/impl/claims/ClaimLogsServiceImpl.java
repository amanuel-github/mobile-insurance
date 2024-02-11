package com.medcoanalytics.lemobileyeservice.service.impl.claims;

import com.medcoanalytics.lemobileyeservice.entity.claims.ClaimLogs;
import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimLogsResponse;
import com.medcoanalytics.lemobileyeservice.repository.claims.ClaimLogsRepository;
import com.medcoanalytics.lemobileyeservice.service.claims.ClaimLogsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClaimLogsServiceImpl implements ClaimLogsService {

	
	@Autowired
	ClaimLogsRepository claimLogsRepository;
	
	/*@Override
	public ResponseEntity<?> createClaimLogs(ClaimLogsRequest claimLogsRequest) {
		 ClaimLogs claimLogs = new ClaimLogs();
			BeanUtils.copyProperties(claimLogsRequest, claimLogs);
			claimLogsRepository.save(claimLogs);
		    return ResponseEntity.ok(new MessageResponse("ClaimLogs registered successfully!"));
	}
*/


	@Override
	public List<ClaimLogsResponse> getClaimLogs(String claimUuid, String searchKey, int page, int limit) {
		if(page > 0) page = page - 1;
		Pageable pageRequest = PageRequest.of(page, limit, Sort.by("id").descending());
		Page<ClaimLogs> claimLogsPage=claimLogsRepository.findAllByClaimUuid(claimUuid,pageRequest);
		long totalPages = claimLogsPage.getTotalPages();
		List<ClaimLogs> claimLogList = claimLogsPage.getContent();
		List<ClaimLogsResponse> claimLogsResponse = new ArrayList<>();
		for (ClaimLogs cl : claimLogList) {
			ClaimLogsResponse clr = new ClaimLogsResponse();
			if(claimLogsResponse.size() == 0)
				clr.setTotalPages(totalPages);
			BeanUtils.copyProperties(cl, clr);
			claimLogsResponse.add(clr);
		}
		return claimLogsResponse;

	
		
	}

	
}
