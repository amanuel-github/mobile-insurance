package com.medcoanalytics.lemobileyeservice.controller.users;


import com.medcoanalytics.lemobileyeservice.payload.request.users.PrivilegeRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.users.PrivilegeResponse;
import com.medcoanalytics.lemobileyeservice.service.users.PrivilegeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lemobileyeservice/users/privilege")
@SecurityRequirement(name = "bearerAuth")
public class PrivilegeController {
	
	 @Autowired
	  AuthenticationManager authenticationManager;

	  
	  @Autowired
	  PrivilegeService privilegeService;
	  
	//// @PreAuthorize("hasRole('ADD_ROLE') or hasRole('ADMIN')")

	  @PostMapping
	  @PreAuthorize("hasRole('Create-Privilege')")
	  public ResponseEntity<?> createPrivilege(@Valid @RequestBody PrivilegeRequest privilegeRequest) {
		  return privilegeService.createPrivilege(privilegeRequest);
	    
	  }
	  
	  @GetMapping(path="/{privilegeUuid}")
	  @PreAuthorize("hasRole('Read-Privilege')")
	  public PrivilegeResponse getRole(@PathVariable String privilegeUuid) {
		  return privilegeService.getPrivilege(privilegeUuid);
	  }

	@GetMapping("/all")
	@PreAuthorize("hasRole('Read-Privileges')")
	public List<PrivilegeResponse> getPrivileges(@RequestParam(value="page", defaultValue = "1") int page,
												 @RequestParam(value="limit", defaultValue = "25") int limit) {
		return privilegeService.getPrivileges(page,limit);
	}
	  @PutMapping(path="/{privilegeUuid}")
	  @PreAuthorize("hasRole('Put-Privilege')")
	  public ResponseEntity<?> updatePrivilege(@PathVariable String privilegeUuid, @Valid @RequestBody PrivilegeRequest privilegeRequest) {
		  return privilegeService.updatePrivilege(privilegeUuid, privilegeRequest);
	    
	  }
	  
	  @DeleteMapping(path="/{privilegeUuid}")
	  @PreAuthorize("hasRole('Delete-Privilege')")
	  public ResponseEntity<?> deletePrivilege(@PathVariable String privilegeUuid) {
		  return privilegeService.deletePrivilege(privilegeUuid);
	    
	  }

	@PostMapping(path = "/search")
	@PreAuthorize("hasRole('Read-Privileges')")
	@Operation(summary = "Search All System Privileges", security = @SecurityRequirement(name = "bearerAuth"))
	public List<PrivilegeResponse> searchPrivileges(@RequestParam("search") String searchKey, @RequestParam(value="page", defaultValue = "1") int page,
													@RequestParam(value="limit", defaultValue = "25") int limit){
		return  privilegeService.searchPrivileges(searchKey,page,limit);

	}



}
