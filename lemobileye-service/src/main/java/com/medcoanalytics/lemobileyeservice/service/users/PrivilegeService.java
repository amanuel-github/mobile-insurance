package com.medcoanalytics.lemobileyeservice.service.users;

import com.medcoanalytics.lemobileyeservice.payload.request.users.PrivilegeRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.users.PrivilegeResponse;
import org.springframework.http.ResponseEntity;
import java.util.List;


public interface PrivilegeService {
	public ResponseEntity<?> createPrivilege(PrivilegeRequest privilegeRequest);
	public ResponseEntity<?> updatePrivilege(String privilegeUuid, PrivilegeRequest roleRequest);
	public ResponseEntity<?> deletePrivilege(String privilegeUuid);
	public PrivilegeResponse getPrivilege(String privilegeUuid);
	public List<PrivilegeResponse> getPrivileges(int page, int limit);
	public List<PrivilegeResponse> searchPrivileges(String searchKey, int page, int limit);


}
