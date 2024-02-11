package com.medcoanalytics.lemobileyeservice.repository.users;

import com.medcoanalytics.lemobileyeservice.entity.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

 Boolean existsByEmail(String email);

User findByEmail(String email);

User findByUserUuid(String userUuid);

void deleteByUserUuid(String userUuid);

Page<User> findAllByPayerUuid(String payerUuid,Pageable pageRequest);

Page<User> findAllByProviderUuid(String providerUuid,Pageable pageRequest);

boolean existsByMobilePhone(String mobilePhone);

User findByEmailAndPasswordResetCode(String email, String passwordResetCode);

User findByEmailVerificationToken(String emailVerificationToken);

Page<User> findByFirstNameContainingOrFatherNameContainingOrGrandFatherNameContainingOrMobilePhoneContainingOrEmailContainingOrUserStatusContaining(
		String searchKey, String searchKey2, String searchKey3, String searchKey4, String searchKey5, String searchKey6,
		Pageable pageableRequest);

Page<User> findByFirstNameContainingAndFatherNameContaining(String firstName, String fatherName,
		Pageable pageableRequest);

Page<User> findByFirstNameContainingAndFatherNameContainingAndGrandFatherNameContaining(String firstName,
		String lastName, String grandFatherName, Pageable pageableRequest);

Page<User> findByFirstNameContainingOrFatherNameContainingOrGrandFatherNameContainingOrMobilePhoneContainingOrEmailContainingOrUserStatusContainingOrUserTypeContaining(
		String searchKey, String searchKey2, String searchKey3, String searchKey4, String searchKey5, String searchKey6,
		String searchKey7, Pageable pageableRequest);


}
