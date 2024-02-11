package com.medcoanalytics.lemobileyeservice.service.users;


import com.medcoanalytics.lemobileyeservice.payload.request.users.LoginRequest;
import com.medcoanalytics.lemobileyeservice.payload.request.users.ResetPasswordRequest;
import com.medcoanalytics.lemobileyeservice.payload.request.users.UploadProfileRequest;
import com.medcoanalytics.lemobileyeservice.payload.request.users.UserRequest;
import com.medcoanalytics.lemobileyeservice.payload.response.users.UserResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import org.springframework.http.ResponseEntity;
import java.io.IOException;
import java.util.List;


public interface UserService {
	
	public ResponseEntity<?> createUser(UserRequest userRequest);
	public ResponseEntity<?> authenticateUser(LoginRequest userRequest);
	public ResponseEntity<?> updateUser(String userUuid, UserRequest userRequest);
	public ResponseEntity<?> deleteUser(String userUuid);
	public UserResponse getUser(String userUuid);
	public List<UserResponse> getUsers(int page, int limit);
	public List<UserResponse> getPayerUsers(int page, int limit);
	public ResponseEntity<?> uploadProfilePicture(UploadProfileRequest requestDetail)throws IOException;
	public ResponseEntity<?> resetPassword(ResetPasswordRequest resetPasswordDetail);
	public ResponseEntity<?> changePassword(ResetPasswordRequest resetPasswordDetail, String userUuid);
	public ResponseEntity<?> verifyAccount(String emailVerificationToken);
	public ResponseEntity<?> reSendVerification(String email) throws AddressException, MessagingException, IOException;
	public ResponseEntity<?> sendPasswordResetCode(String email) throws AddressException, MessagingException, IOException;
	public ResponseEntity<?> checkResetCode(ResetPasswordRequest resetPasswordDetail);
	public List<UserResponse> searchUsers(String searchKey, int page, int limit);
	

}
