package com.gengulay.spring.web.dao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.gengulay.spring.validation.constraints.ValidEmail;

public class User {

	@NotBlank(message = "username cannot be blank")
	@Size(min = 8, max = 15)
	@Pattern(regexp = "^\\w{8,}$", message = "username cannot contain special characters")
	private String username;

	@NotBlank(message = "password cannot be blank")
	@Pattern(regexp = "^\\S+$", message = "password cannot contain spaces")
	@Size(min = 8, max = 15, message = "password must be 8-15 characters long")
	private String password;

	@ValidEmail(message = "this is not a valid email")
	private String email;
	boolean enabled = false;
	private String authority;

	public User() {

	}

	public User(String username, String password, boolean enabled, String authority, String email) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}