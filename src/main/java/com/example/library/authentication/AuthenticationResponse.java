package com.example.library.authentication;

import java.util.Objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
	
	private String token;
	
	public AuthenticationResponse(String token) {
		
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "AuthenticationResponse [token=" + token + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(token);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthenticationResponse other = (AuthenticationResponse) obj;
		return Objects.equals(token, other.token);
	}
	
}
