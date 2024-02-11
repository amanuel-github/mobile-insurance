package com.medcoanalytics.lemobileyeservice.service.impl.users;


import com.medcoanalytics.lemobileyeservice.entity.users.Role;
import com.medcoanalytics.lemobileyeservice.entity.users.User;
import com.medcoanalytics.lemobileyeservice.payload.request.users.*;
import com.medcoanalytics.lemobileyeservice.payload.response.MessageResponse;
import com.medcoanalytics.lemobileyeservice.payload.response.users.UserResponse;
import com.medcoanalytics.lemobileyeservice.repository.users.RoleRepository;
import com.medcoanalytics.lemobileyeservice.repository.users.UserRepository;
import com.medcoanalytics.lemobileyeservice.security.jwt.JwtUtils;
import com.medcoanalytics.lemobileyeservice.security.services.UserDetailsImpl;
import com.medcoanalytics.lemobileyeservice.security.ui.response.JwtResponse;
import com.medcoanalytics.lemobileyeservice.service.users.UserService;
import com.medcoanalytics.lemobileyeservice.shared.email.SendEmail;
import com.medcoanalytics.lemobileyeservice.shared.random.GenerateRandomString;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

	 @Autowired
	 UserRepository userRepository;
	 
	 @Autowired
	 RoleRepository roleRepository;
	 
	 @Autowired
	  PasswordEncoder encoder;
	 
	  @Autowired
	  AuthenticationManager authenticationManager;
     
	  @Autowired
	  JwtUtils jwtUtils;
	  
		@Autowired
		SendEmail sendMailComponent;
		
		@Autowired
		GenerateRandomString generateRandomString;
	
	  
	  @Value("${file.upload-dir}")
	    private String uploadDirectory;
	  
	  @Value("${app.HostDomain}")
	    private String applicationHostDomain;
		

	@Override
	public ResponseEntity<?> createUser(UserRequest userRequest) {
		 if (userRepository.existsByEmail(userRequest.getEmail())) {
		      return ResponseEntity
		          .badRequest()
		          .body(new MessageResponse("Error: Email is already in use!"));
		    }
		 if (userRepository.existsByMobilePhone(userRequest.getMobilePhone())) {
		      return ResponseEntity
		          .badRequest()
		          .body(new MessageResponse("Error: Mobile Phone is already in use!"));
		    }
		 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication == null ||
	                !authentication.isAuthenticated() ||
	                authentication instanceof AnonymousAuthenticationToken) {
	        	throw new RuntimeException("Login to  get authorized.");
	        }
	        
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	    
		String payerUuid=userDetails.getPayerUuid();
		String providerUuid=userDetails.getProviderUuid();
	
		//TODO("After clean up do conditions for type of user: payer, provider and admin")
		if ((payerUuid.isEmpty()|| payerUuid==null) && (providerUuid.isEmpty()||providerUuid==null) ) {
			if(userRequest.getPayerUuid()!=null)
			payerUuid=userRequest.getPayerUuid().toString();
			if(userRequest.getProviderUuid()!=null)
			providerUuid=userRequest.getProviderUuid().toString();
		}
		 
		 User user = new User();
			BeanUtils.copyProperties(userRequest, user);
			user.setPayerUuid(payerUuid);
			user.setProviderUuid(providerUuid);
			user.setPassword(encoder.encode(userRequest.getPassword()));
			userRepository.save(user);
		    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@Override
	public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
		  Authentication authentication = authenticationManager.authenticate(
			        new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

			    SecurityContextHolder.getContext().setAuthentication(authentication);
			    String jwt = jwtUtils.generateJwtToken(authentication);
			    
			    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
			    List<String> roles = userDetails.getAuthorities().stream()
			        .map(item -> item.getAuthority())
			        .collect(Collectors.toList());
			    
			
			    Optional<Role> role = roleRepository.findById(userDetails.getRoleId());
		        String roleName = role.orElse(new Role()).getRoleName();
			 
			    return ResponseEntity.ok(new JwtResponse(jwt,
			                         userDetails.getUserUuid(),userDetails.getEmail(),  roleName, userDetails.getTitle(), userDetails.getFirstName(),
			                         userDetails.getFatherName(), userDetails.getGrandFatherName(), userDetails.getGender(),
			                         userDetails.getMobilePhone(), userDetails.getUserStatus(), userDetails.getUserType(),
			                         userDetails.getProviderUuid(), userDetails.getPayerUuid(), userDetails.getProfilePicture(),
			                         
			                         roles));
	}

	@Override
	public ResponseEntity<?> updateUser(String userUuid, UserRequest userRequest) {
	
         User user = userRepository.findByUserUuid(userUuid);
		
		 if(user == null) 
			throw new RuntimeException("User not found.");
		
		user.setFirstName(userRequest.getFirstName());
		user.setFatherName(userRequest.getFatherName());
		user.setGrandFatherName(userRequest.getGrandFatherName());
		user.setMobilePhone(userRequest.getMobilePhone());
		 userRepository.save(user);
		 return ResponseEntity.ok(new MessageResponse("User Updated successfully!"));
	}

	@Override
	public ResponseEntity<?> deleteUser(String userUuid) {
		  User user = userRepository.findByUserUuid(userUuid);
			 if(user == null) 
				throw new RuntimeException("User not found.");
			 user.setDeleted(true);
		 userRepository.save(user);
		 return ResponseEntity.ok(new MessageResponse("User soft deleted successfully!"));
	}

	@Override
	public UserResponse getUser(String userUuid) {
		User user= userRepository.findByUserUuid(userUuid);
		
		 Optional<Role> role = roleRepository.findById(user.getRoleId());
	        String roleName = role.orElse(new Role()).getRoleName();
		UserResponse userResponse = new UserResponse();
		if (role !=null)
		userResponse.setRoleName(roleName);
		BeanUtils.copyProperties(user, userResponse); 
		return userResponse;
		
	}

	@Override
	public List<UserResponse> getPayerUsers(int page, int limit) {
		if(page > 0) page = page - 1;

		Pageable pageRequest = PageRequest.of(page, limit, Sort.by("id").descending());
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null ||
				!authentication.isAuthenticated() ||
				authentication instanceof AnonymousAuthenticationToken) {
			throw new RuntimeException("Login to  get authorized.");
		}

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		String payerUuid=userDetails.getPayerUuid();
		Page<User> usersPage= userRepository.findAllByPayerUuid(payerUuid,pageRequest);
		List<User> userList= usersPage.getContent();
		long totalPages = usersPage.getTotalPages();
		 List<UserResponse> userResponse = new ArrayList<>();
		 for (User u : userList) {
		        UserResponse ur = new UserResponse();
			 if(userResponse.size() == 0)
				 ur.setTotalPages(totalPages);
		        BeanUtils.copyProperties(u, ur);
		        userResponse.add(ur);
		    }
		    return userResponse;
		
	}

	
	@Override
	public List<UserResponse> getUsers(int page, int limit) {
		
	    if(page > 0) page = page - 1; 
	   
	    Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("id").descending());
	    Page<User> usersPage = userRepository.findAll(pageableRequest);
	    int totalPages = usersPage.getTotalPages();
	    List<User> userList = usersPage.getContent();
	    List<UserResponse> userResponse = new ArrayList<>();
	    for (User u : userList) {
	        UserResponse ur = new UserResponse();
	        Optional<Role> role = roleRepository.findById(u.getRoleId());
	        String roleName = role.orElse(new Role()).getRoleName();
			ur.setRoleName(roleName);
	        if(userResponse.size() == 0) 
	        	ur.setTotalPages(totalPages);
	        BeanUtils.copyProperties(u, ur);
	        userResponse.add(ur);
	    }
	   
	    return userResponse;
	}
	
	
	@Override
	public List<UserResponse> searchUsers(String searchKey, int page, int limit) {

	    if(page > 0) page = page - 1; 
	    String[] searchKeys = searchKey.split(" ");
	    
	    Pageable pageableRequest = PageRequest.of(page, limit, Sort.by("id").descending());
	    int countSpaces = StringUtils.countOccurrencesOf(searchKey, " ");
	    
	    Page<User> usersPage = null;
	    
	    if(countSpaces == 0) {
	    	usersPage = userRepository.findByFirstNameContainingOrFatherNameContainingOrGrandFatherNameContainingOrMobilePhoneContainingOrEmailContainingOrUserStatusContainingOrUserTypeContaining(searchKey,searchKey,searchKey,searchKey,searchKey,searchKey, searchKey, pageableRequest);
	    }
	    else if(countSpaces == 1){
	    	String firstName = searchKeys[0];
	    	String fatherName = searchKeys[1];
	    	usersPage = userRepository.findByFirstNameContainingAndFatherNameContaining(firstName,fatherName,pageableRequest);
	    }
	    else if(countSpaces == 2) {
	    	String firstName = searchKeys[0];
	    	String fatherName = searchKeys[1];
	    	String grandFatherName = searchKeys[2];
	    	usersPage = userRepository.findByFirstNameContainingAndFatherNameContainingAndGrandFatherNameContaining(firstName,fatherName,grandFatherName,pageableRequest);
	    }
	    
	   
	    int totalPages = usersPage.getTotalPages();
	    List<User> users = usersPage.getContent();
	    List<UserResponse> userResponse = new ArrayList<>();
	    for (User u : users) {
	        UserResponse ur = new UserResponse();
	        Optional<Role> role = roleRepository.findById(u.getRoleId());
	        String roleName = role.orElse(new Role()).getRoleName();
			ur.setRoleName(roleName);
	        if(userResponse.size() == 0) 
	        	ur.setTotalPages(totalPages);
	        BeanUtils.copyProperties(u, ur);
	        userResponse.add(ur);
	    }
	   
	    return userResponse;
	}

	
	@Override
	public ResponseEntity<?> uploadProfilePicture(UploadProfileRequest requestDetail) throws IOException {
		String uploadDir = uploadDirectory + "/profiles/";
		File directory = new File(uploadDir);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		
		String returnValue = "Image not Saved";
		byte[] bytes = requestDetail.getProfilePicture().getBytes();
		
		String fileName = requestDetail.getProfilePicture().getOriginalFilename();
		String extention = (fileName.substring(fileName.lastIndexOf(".") + 1)).toLowerCase();
		String newFileName = requestDetail.getUserUuid() + "." +  extention;
		Path path = Paths.get(uploadDir + newFileName);
	    Files.write(path, bytes);
	    
	    User user = userRepository.findByUserUuid(requestDetail.getUserUuid());
		
		if(user == null) 
			throw new RuntimeException("User not found.");
		
		user.setProfilePicture(newFileName);	
		User updatesUserDetails = userRepository.save(user);
		if(updatesUserDetails.getProfilePicture() != null) {
			returnValue = "Profile picture Saved";
		}
		
		return ResponseEntity.ok(new MessageResponse(returnValue));
	}

	@Override
	public ResponseEntity<?> resetPassword(ResetPasswordRequest resetPasswordDetail) {
		String returnValue = "Password not changed";
		User userEntity = userRepository.findByEmailAndPasswordResetCode(resetPasswordDetail.getEmail(),resetPasswordDetail.getPasswordResetCode());
		if(userEntity == null) 	throw new RuntimeException("Password reset code not found.");
		
		userEntity.setPassword(encoder.encode(resetPasswordDetail.getNewPassword()));
		User passworUpdated = userRepository.save(userEntity);
		if(passworUpdated != null) {
			returnValue = "Password changed successfully";
		}
		return ResponseEntity.ok(new MessageResponse(returnValue));
	}

	@Override
	public ResponseEntity<?> changePassword(ResetPasswordRequest resetPasswordDetail, String userUuid) {
		String returnValue = "Password not changed";
		User userEntity = userRepository.findByUserUuid(userUuid);
		if(userEntity == null) 	throw new RuntimeException("User not found.");
		
		userEntity.setPassword(encoder.encode(resetPasswordDetail.getNewPassword()));
		User passworUpdated = userRepository.save(userEntity);
		if(passworUpdated != null) {
			returnValue = "Password changed successfully";
		}
		return ResponseEntity.ok(new MessageResponse(returnValue));
	}

	@Override
	public ResponseEntity<?> verifyAccount(String emailVerificationToken) {
		String returnValue = "";
		User user = userRepository.findByEmailVerificationToken(emailVerificationToken);
		if(user== null) throw new RuntimeException("User not found.");
		
		String userStatus = "Active";
		user.setUserStatus(userStatus);
		User updatedUser = userRepository.save(user);
		if(updatedUser.getUserStatus() == "Active") {
			returnValue = "Account Verified Successfully";
		}
		return ResponseEntity.ok(new MessageResponse(returnValue));
	}

	@Override
	public ResponseEntity<?> reSendVerification(String email) throws AddressException, MessagingException, IOException {
		User userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new RuntimeException("User not found.");

		String mailSubject = "ClaimConnect account Verification";
		String mailBody = "<b>Verify your ClaimConnect Account</b><br><br> Follow this link --> <a href='" + applicationHostDomain + "/verifyaccount?verificationToken=" + userEntity.getEmailVerificationToken() + "'><b><i>Click me to Verify</i></b></a><br><br> <span style='color:red; font-size:12px;'> Don't reply to this email !</span>";
		SendEmailRequest sendMail = new SendEmailRequest();
		sendMail.setToAddress(email);
		sendMail.setSubject(mailSubject);
		sendMail.setBody(mailBody);
		String returnValue = sendMailComponent.sendMail(sendMail);
		return ResponseEntity.ok(new MessageResponse(returnValue));
		
	}

	@Override
	public ResponseEntity<?> sendPasswordResetCode(String email) throws AddressException, MessagingException, IOException {
		
		String resetCode = generateRandomString.generateAccountId(6);
		
		User userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new RuntimeException("User not found.");
		
		userEntity.setPasswordResetCode(resetCode);
		userRepository.save(userEntity);
		
		String mailSubject = "ClaimConnect account Password Reset";
		String mailBody = "<b>Reset your ClaimConnect Account Password</b><br><br> Enter the code or Follow the link <br> Reset Code : <b>" + resetCode + "</b> <br> <a href='" + applicationHostDomain + "/resetpassword?resetCode=" + resetCode + "'><b><i>Click me to Reset Password</i></b></a><br><br> <span style='color:red; font-size:12px;'> Don't reply to this email !</span>";
		SendEmailRequest sendMail = new SendEmailRequest();
		sendMail.setToAddress(email);
		sendMail.setSubject(mailSubject);
		sendMail.setBody(mailBody);
		String emailStatus = sendMailComponent.sendMail(sendMail);
		return ResponseEntity.ok(new MessageResponse("Password Reset Code sent"+emailStatus));
		
	}

	@Override
	public ResponseEntity <?> checkResetCode(ResetPasswordRequest resetPasswordDetail) {
		User userEntity = userRepository.findByEmailAndPasswordResetCode(resetPasswordDetail.getEmail(),resetPasswordDetail.getPasswordResetCode());
		if(userEntity == null) throw new RuntimeException("Invalid Reset Code.");
		return ResponseEntity.ok(new MessageResponse("Reset Code is valid"));
		
	}

}
