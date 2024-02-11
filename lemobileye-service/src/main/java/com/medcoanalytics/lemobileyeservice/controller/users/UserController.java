package com.medcoanalytics.lemobileyeservice.controller.users;


import com.medcoanalytics.lemobileyeservice.payload.request.users.LoginRequest;
import com.medcoanalytics.lemobileyeservice.payload.request.users.ResetPasswordRequest;
import com.medcoanalytics.lemobileyeservice.payload.request.users.UploadProfileRequest;
import com.medcoanalytics.lemobileyeservice.payload.request.users.UserRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.users.UserResponse;
import com.medcoanalytics.lemobileyeservice.service.users.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lemobileyeservice/users")

public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		return userService.authenticateUser(loginRequest);
	}

	@PostMapping("/signup")
	@Operation(summary = "Add System User", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest signUpRequest) {
		return userService.createUser(signUpRequest);
	}

	@PutMapping(path = "/{userUuid}")
	@PreAuthorize("hasRole('Update-User')")
	@Operation(summary = "Update System User", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<?> updateUser(@PathVariable String userUuid, @RequestBody UserRequest userRequest) {
		return userService.updateUser(userUuid, userRequest);

	}

	@GetMapping(path = "/{userUuid}")
	@PreAuthorize("hasRole('Read-User')")
	@Operation(summary = "Read System User", security = @SecurityRequirement(name = "bearerAuth"))
	public UserResponse getUser(@PathVariable String userUuid) {
		return userService.getUser(userUuid);

	}

	@DeleteMapping(path = "/{userUuid}")
	@PreAuthorize("hasRole('Delete-User')")
	@Operation(summary = "Delete System User", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<?> deleteUser(@PathVariable String userUuid) {
		return userService.deleteUser(userUuid);

	}

	@GetMapping("")
	@PreAuthorize("hasRole('Read-Payer-Users')")
	@Operation(summary = "Read Payer Users", security = @SecurityRequirement(name = "bearerAuth"))
	public List<UserResponse> getPayerUsers(@RequestParam(value="page", defaultValue = "1") int page,
											@RequestParam(value="limit", defaultValue = "25") int limit) {
		return userService.getPayerUsers(page,limit);

	}



	@GetMapping(path = "/all")
	@PreAuthorize("hasRole('Read-Users')")
	@Operation(summary = "Read All System Users", security = @SecurityRequirement(name = "bearerAuth"))
	public List<UserResponse> getUsers(@RequestParam(value="page", defaultValue = "1") int page,
			   @RequestParam(value="limit", defaultValue = "25") int limit){
		return userService.getUsers(page,limit);

	}
	
	@PostMapping(path = "/search")
	@PreAuthorize("hasRole('Read-Users')")
	@Operation(summary = "Search All System Users", security = @SecurityRequirement(name = "bearerAuth"))
	public List<UserResponse> searchUsers(@RequestParam("search") String searchKey, @RequestParam(value="page", defaultValue = "1") int page,
			   @RequestParam(value="limit", defaultValue = "25") int limit){
		return userService.searchUsers(searchKey,page,limit);

	}
	

	@PostMapping(path = "/uploadprofile")
	@PreAuthorize("hasRole('Change-User-Profile')")
	@Operation(summary = "Change-User-Profile", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<?> uploadProfilePicture(@ModelAttribute UploadProfileRequest requestDetail)
			throws IOException {
		return userService.uploadProfilePicture(requestDetail);
	}

	
	@PutMapping(path="/resetpassword")
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordDetail){
		return userService.resetPassword(resetPasswordDetail);
		
	}
	
	@PutMapping(path = "/changepassword/{userUuid}")
	@PreAuthorize("hasRole('Change-Password')")
	@Operation(summary = "Change-Password", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<?> changePassword(@RequestBody ResetPasswordRequest resetPasswordDetail,
			@PathVariable String userUuid) {
		return userService.changePassword(resetPasswordDetail, userUuid);

	}


	@GetMapping(path = "/email/verification/{emailVerificationToken}")
	@PreAuthorize("hasRole('Email-Verification')")
	@Operation(summary = "Email-Verification", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<?> verifyAccount(@PathVariable String emailVerificationToken) {
		return userService.verifyAccount(emailVerificationToken);

	}

	@PutMapping(path = "/email/verification/resend/{email}")
	@PreAuthorize("hasRole('Email-Verification')")
	@Operation(summary = "Send Email Verification Code", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<?> reSendVerification(@PathVariable String email)
			throws AddressException, MessagingException, IOException {
		return userService.reSendVerification(email);

	}

	@PutMapping(path = "/password/sendresetcode/{email}")
	public ResponseEntity<?> resetPassword(@PathVariable String email)
			throws AddressException, MessagingException, IOException {
		return userService.sendPasswordResetCode(email);

	}

	@PostMapping(path = "/password/checkresetcode")
	public ResponseEntity<?> checkResetCode(@RequestBody ResetPasswordRequest resetPasswordDetail) {
		return userService.checkResetCode(resetPasswordDetail);
		
	}

}
