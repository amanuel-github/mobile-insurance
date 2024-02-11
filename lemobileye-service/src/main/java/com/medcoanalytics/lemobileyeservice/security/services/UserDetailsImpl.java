package com.medcoanalytics.lemobileyeservice.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medcoanalytics.lemobileyeservice.entity.users.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private String userUuid;
	private String email;
	@JsonIgnore
	private String password;
	private Integer roleId;
	private String title;
	private String firstName;
	private String fatherName;
	private String grandFatherName;
	private String gender;
	private String mobilePhone;
	private String userStatus;
	private String userType;
	private String providerUuid;
	private String payerUuid;
	private String profilePicture;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String userUuid, String email, String password, Integer roleId, String title, String firstName, 
			String fatherName, String grandFatherName, String gender, String mobilePhone, String userStatus, String userType,
			String payerUuid, String providerUuid, String profilePicture,
			Collection<? extends GrantedAuthority> authorities) {
		this.userUuid = userUuid;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.roleId=roleId; this.title=title; this.firstName=firstName; this.fatherName=fatherName;
				this.grandFatherName=grandFatherName; this.gender=gender; this.mobilePhone=mobilePhone; this.userStatus=userStatus;
				this.userType=userType; this.payerUuid=payerUuid; this.providerUuid=providerUuid; this.profilePicture=profilePicture;
	}

	public static UserDetailsImpl build(User user, List<String> privilegesForRole) {

		List<GrantedAuthority> authorities = privilegesForRole.stream().map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

		return new UserDetailsImpl(user.getUserUuid(), user.getEmail(), user.getPassword(), user.getRoleId(),
				user.getTitle(), user.getFirstName(), user.getFatherName(), user.getGrandFatherName(),user.getGender(),
				user.getMobilePhone(), user.getUserStatus(), user.getUserType(), user.getPayerUuid(), user.getProviderUuid(), user.getProfilePicture(),
				authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getUserUuid() {
		return userUuid;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(userUuid, user.userUuid);
	}
	@Override
	public String getUsername() {
		return email;
	}

}
