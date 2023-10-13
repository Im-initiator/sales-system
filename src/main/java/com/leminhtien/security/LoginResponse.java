package com.leminhtien.security;

public class LoginResponse {
	private String accessToken;
	private String tokenType = "Bearer";

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public LoginResponse(String accessToken) {
		this.accessToken = accessToken;
	}
}
