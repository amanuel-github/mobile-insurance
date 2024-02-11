package com.medcoanalytics.lemobileyeservice.payload.response.users;


import com.medcoanalytics.lemobileyeservice.shared.audit.Audit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse extends Audit {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -257037957967607541L;
	private String userUuid;
	  private String email;
	  private String title;
	  private String firstName;
	  private String fatherName;
	  private String grandFatherName;
	  private String Gender;
	  
	  private String mobilePhone;
	  private String userStatus;
	  private String userType;
	  private String providerUuid;
	  private String payerUuid;
	  private String profilePicture;
	  
	  private boolean emailVerificationStatus;
	  private boolean phoneVerificationStatus;
	  private boolean isDeleted;
	  private Integer roleId;
	  private String roleName;
	  private long totalPages;

}
