package com.medcoanalytics.lemobileyeservice.service.impl.users;


import com.medcoanalytics.lemobileyeservice.entity.users.Privilege;
import com.medcoanalytics.lemobileyeservice.payload.request.users.PrivilegeRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.MessageResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.users.PrivilegeResponse;
import com.medcoanalytics.lemobileyeservice.repository.users.PrivilegeRepository;
import com.medcoanalytics.lemobileyeservice.service.users.PrivilegeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {
  
	@Autowired
	PrivilegeRepository privilegeRepository;
	
	@Override
	public ResponseEntity<?> createPrivilege(PrivilegeRequest privilege) {
		
		if (privilegeRepository.existsByPrivilegeName(privilege.getPrivilegeName())) {
		      return ResponseEntity
		          .badRequest()
		          .body(new MessageResponse("Error: Role Name is already registered!"));
		    }
		    if (privilegeRepository.existsByPrivilegeDescription(privilege.getPrivilegeDescription())) {
		        return ResponseEntity
		            .badRequest()
		            .body(new MessageResponse("Error: Role description is already registered!"));
		      }
	  
		    Privilege priv = new Privilege();
			BeanUtils.copyProperties(privilege, priv);
		privilegeRepository.save(priv);
		
		 return ResponseEntity.ok(new MessageResponse("Privilege registered successfully!"));
		
	}

	@Override
	public ResponseEntity<?> updatePrivilege(String privilegeUuid, PrivilegeRequest privilegeRequest) {
		 Privilege privilege = privilegeRepository.findByPrivilegeUuid(privilegeUuid);
		 List<Privilege> privilegeName = privilegeRepository.findAllByPrivilegeName(privilegeRequest.getPrivilegeName());
		 List<Privilege> privDesc = privilegeRepository.findAllByPrivilegeDescription(privilegeRequest.getPrivilegeDescription());
			
		 if(privilege == null) 
			throw new RuntimeException("Privilege not found.");
		 if (privDesc.size() >=2 || privilegeName.size() >=2)
			 throw new RuntimeException("Privilege  name or description is exist. Update privilege to have unique name and description");
		
		 privilege.setPrivilegeName(privilegeRequest.getPrivilegeName());
		 privilege.setPrivilegeDescription(privilegeRequest.getPrivilegeDescription());
		 privilege.setPrivilegeCategory(privilegeRequest.getPrivilegeCategory());
		 
		 privilegeRepository.save(privilege);
			
		 return ResponseEntity.ok(new MessageResponse("Privilege Updated successfully!"));
	}

	@Override
	public ResponseEntity<?> deletePrivilege(String privilegeString) {
		Privilege privilege = privilegeRepository.findByPrivilegeUuid(privilegeString);
		 if(privilege == null) 
			throw new RuntimeException("Privilege not found.");
		 privilegeRepository.delete(privilege);
	 return ResponseEntity.ok(new MessageResponse("Privilege deleted permanently!"));
	}

	@Override
	public PrivilegeResponse getPrivilege(String privilegeUuid) {
	    Privilege priv = privilegeRepository.findByPrivilegeUuid(privilegeUuid);
	    if(priv == null) 
			throw new RuntimeException("PrivilegeUuid not found.");
	    PrivilegeResponse privResponse = new PrivilegeResponse();
	    privResponse.setPrivilegeUuid(priv.getPrivilegeUuid());
	    privResponse.setPrivilegeName(priv.getPrivilegeName());
	    privResponse.setPrivilegeDescription(priv.getPrivilegeDescription());
	    return privResponse;
	}

	@Override
	public List<PrivilegeResponse> getPrivileges(int page, int limit) {
		if(page > 0) page = page - 1;
		Pageable pageRequest = PageRequest.of(page, limit, Sort.by("id").descending());
		Page<Privilege> privilegesPage = privilegeRepository.findAll( pageRequest);
		long totalPages = privilegesPage.getTotalPages();
		List<Privilege> privilegeList = privilegesPage.getContent();
		List<PrivilegeResponse> privResponse = new ArrayList<>();
		for (Privilege p : privilegeList) {
			PrivilegeResponse pr = new PrivilegeResponse();
			if(privResponse.size()==0)
				pr.setTotalPages(totalPages);
			BeanUtils.copyProperties(p, pr);
			privResponse.add(pr);
		}
		return privResponse;
	}
	@Override
	public List<PrivilegeResponse> searchPrivileges(String searchTerm, int page, int limit) {

		if(page > 0) page = page - 1;

		Pageable pageRequest = PageRequest.of(page, limit, Sort.by("id").descending());

		Page<Privilege> PrivilegesPage = privilegeRepository.findByPrivilegeNameContainingOrPrivilegeDescriptionContainingOrPrivilegeCategoryContaining(searchTerm,searchTerm,searchTerm, pageRequest);


		long totalPages = PrivilegesPage.getTotalPages();
		List<Privilege> privilegeList = PrivilegesPage.getContent();
		List<PrivilegeResponse> privilegeResponse = new ArrayList<>();
		for (Privilege p : privilegeList) {
			PrivilegeResponse pr = new PrivilegeResponse();
			if(privilegeResponse.size() == 0)
				pr.setTotalPages(totalPages);
			BeanUtils.copyProperties(p, pr);
			privilegeResponse.add(pr);
		}

		return privilegeResponse;
	}


}
