package com.medcoanalytics.lemobileyeservice.payload.request.users;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserRequest {


  @NotBlank
  @Size(max = 50)
  @Email
  private String email;


  @NotBlank
  @Size(min = 6, max = 40)
  private String password;
  
  @NotBlank
  @Size(min = 2, max = 25)
  private String title;
  
  @NotBlank
  @Size(min = 2, max = 25)
  private String firstName;
  
  @NotBlank
  @Size(min = 2, max = 25)
  private String fatherName;
  
  @NotBlank
  @Size(min = 2, max = 25)
  private String grandFatherName;
  
  @NotBlank
  @Size(min = 1, max = 10)
  private String Gender;
  
  @NotBlank
  @Size(min = 9, max = 13) 
  private String mobilePhone;
  
  @NotBlank
  @Size(min = 3, max = 25) 
  private String userStatus;
  
  @NotBlank
  @Size(min = 2, max = 25) 
  private String userType;
  
  private UUID providerUuid;
  private UUID payerUuid;
  
 
  
  @NotNull
  private Integer roleId;
  

  
  
}
