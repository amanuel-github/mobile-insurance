package com.medcoanalytics.lemobileyeservice.entity.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.medcoanalytics.lemobileyeservice.shared.audit.Audit;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "password"), @UniqueConstraint(columnNames = "mobilePhone") })
public class User extends Audit {


	/**
	 * 
	 */
	private static final long serialVersionUID = -8809180659179591610L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 36, max = 40)
	private String userUuid = UUID.randomUUID().toString();

	@NotBlank
	@Size(min = 5, max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(min = 5, max = 120)
	private String password;

	@NotNull
	private Integer roleId;

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
	private String gender;

	@NotBlank
	@Size(min = 9, max = 13)
	private String mobilePhone;

	@NotBlank
	@Size(min = 3, max = 25)
	private String userStatus;

	@NotBlank
	@Size(min = 3, max = 25)
	private String userType;

	@Size( max = 40)
	private String providerUuid;

	@Size(max = 40)
	private String payerUuid;

	private String passwordResetCode;
	private String emailVerificationToken;
	private String profilePicture;



	@Column(columnDefinition = "boolean default false")
	private boolean isDeleted;

	public User(String email, String password, Integer roleId) {
		this.email = email;
		this.password = password;
		this.roleId = roleId;
	}

}
