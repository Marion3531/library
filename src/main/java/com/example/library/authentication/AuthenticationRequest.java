package com.example.library.authentication;

import java.util.Objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {
	
	private String username;
	private String password;
	
	public AuthenticationRequest(String username, String password) {

		this.username = username;
		this.password = password;
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
	
	@Override
	public String toString() {
		return "AuthenticationRequest [username=" + username + ", password=" + password + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(username, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthenticationRequest other = (AuthenticationRequest) obj;
		return Objects.equals(username, other.username) && Objects.equals(password, other.password);
	}

}
