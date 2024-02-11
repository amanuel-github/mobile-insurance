package com.medcoanalytics.lemobileyeservice.service.claims;


import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimLogsResponse;

import java.util.List;

public interface ClaimLogsService {
	
	public List<ClaimLogsResponse> getClaimLogs(String contractUuid, String searchKey, int page, int limit);


}
