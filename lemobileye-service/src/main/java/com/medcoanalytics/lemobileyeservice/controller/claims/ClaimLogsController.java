package com.medcoanalytics.lemobileyeservice.controller.claims;

import com.medcoanalytics.lemobileyeservice.payload.response.claims.ClaimLogsResponse;
import com.medcoanalytics.lemobileyeservice.service.claims.ClaimLogsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lemobileyeservice/claimslog")
@SecurityRequirement(name = "bearerAuth")

public class ClaimLogsController {


	@Autowired
	ClaimLogsService claimLogsService;
	
	  @GetMapping(path="/{claimUuid}")
	  @PreAuthorize("hasRole('Read-Claim-Logs')")
	  public List<ClaimLogsResponse> getClaimLogs(@PathVariable String claimUuid , @RequestParam(name = "search", required = false)  String searchKey,
												  @RequestParam(value="page", defaultValue = "1") int page,
												  @RequestParam(value="limit", defaultValue = "25") int limit) {
		  return claimLogsService.getClaimLogs(claimUuid ,searchKey, page, limit);
	  }
}
