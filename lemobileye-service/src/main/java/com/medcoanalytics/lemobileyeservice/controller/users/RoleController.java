package com.medcoanalytics.lemobileyeservice.controller.users;


import com.medcoanalytics.lemobileyeservice.payload.request.users.AddRolePrivilegesRequest;
import com.medcoanalytics.lemobileyeservice.payload.request.users.RoleRequest;
import com.medcoanalytics.lemobileyeservice.payload.request.users.RoleUpdateRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.users.RoleResponse;
import com.medcoanalytics.lemobileyeservice.service.users.RoleService;
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
@RequestMapping("/api/lemobileyeservice/users/role")
@SecurityRequirement(name = "bearerAuth")
public class RoleController {

  @Autowired
  AuthenticationManager authenticationManager;

  
  @Autowired
  RoleService roleService;
  
  @PostMapping
  @PreAuthorize("hasRole('Create-Role')")
  public ResponseEntity<?> createRole(@Valid @RequestBody RoleRequest roleRequest) {
	  return roleService.createRole(roleRequest);
    
  }
  
  @PutMapping(path="/{roleUuid}")
  @PreAuthorize("hasRole('Update-Role')")
  public ResponseEntity<?> updateRole(@PathVariable String roleUuid, @Valid @RequestBody RoleUpdateRequest roleUpdateRequest) {
	  return roleService.updateRole(roleUuid, roleUpdateRequest);
  }
  
  @PutMapping("privileges/{roleUuid}")
  @PreAuthorize("hasRole('Update-Role')")
  public ResponseEntity<?> addRolePrivileges(@PathVariable String roleUuid, @Valid @RequestBody AddRolePrivilegesRequest rolePrivilegesRequest) {
	  return roleService.addRolePrivileges(roleUuid, rolePrivilegesRequest);
  }
  
  @GetMapping(path="/{roleUuid}")
  @PreAuthorize("hasRole('Read-Role')")
  public RoleResponse getRole(@PathVariable String roleUuid) {
	  return roleService.getRole(roleUuid);
  }

  @GetMapping("/all")
  @PreAuthorize("hasRole('Read-Roles')")
  public List<RoleResponse> getRoles(@RequestParam(value="page", defaultValue = "1") int page,
                                     @RequestParam(value="limit", defaultValue = "25") int limit) {
    return roleService.getRoles(page,limit);
  }
  @DeleteMapping(path="/{roleUuid}")
  @PreAuthorize("hasRole('Delete-Role')")
  public ResponseEntity<?> deleteRole(@PathVariable String roleUuid) {
	  return roleService.deleteRole(roleUuid);
  }
  
  @PutMapping("delete/privileges/{roleUuid}")
  @PreAuthorize("hasRole('Delete-Role')")
  public ResponseEntity<?> deleteRolePrivileges(@PathVariable String roleUuid, @Valid @RequestBody AddRolePrivilegesRequest rolePrivilegesRequest) {
	  return roleService.deleteRolePrivileges(roleUuid, rolePrivilegesRequest);
  }
  @PostMapping(path = "/search")
  @PreAuthorize("hasRole('Read-Roles')")
  @Operation(summary = "Search All System Roles", security = @SecurityRequirement(name = "bearerAuth"))
  public List<RoleResponse> searchRoles(@RequestParam("search") String searchKey, @RequestParam(value="page", defaultValue = "1") int page,
                                        @RequestParam(value="limit", defaultValue = "25") int limit){
    return roleService.searchRoles(searchKey,page,limit);

  }
  
}
