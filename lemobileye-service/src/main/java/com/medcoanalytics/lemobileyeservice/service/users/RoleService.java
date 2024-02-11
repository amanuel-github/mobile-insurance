package com.medcoanalytics.lemobileyeservice.service.users;

import com.medcoanalytics.lemobileyeservice.payload.request.users.AddRolePrivilegesRequest;
import com.medcoanalytics.lemobileyeservice.payload.request.users.RoleRequest;
import com.medcoanalytics.lemobileyeservice.payload.request.users.RoleUpdateRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.users.RoleResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface RoleService {
public ResponseEntity<?> createRole(RoleRequest roleRequest);
public ResponseEntity<?> updateRole(String roleUuid, @Valid RoleUpdateRequest roleUpdateRequest);
public ResponseEntity<?> deleteRole(String roleString);
public RoleResponse getRole(String roleUuid);
    public List<RoleResponse> getRoles(int page, int limit);
public ResponseEntity<?> addRolePrivileges(String roleUuid, @Valid AddRolePrivilegesRequest rolePrivilegesRequest);
public ResponseEntity<?> deleteRolePrivileges(String roleUuid, @Valid AddRolePrivilegesRequest rolePrivilegesRequest);
public List<RoleResponse> searchRoles(String searchKey, int page, int limit);

}
