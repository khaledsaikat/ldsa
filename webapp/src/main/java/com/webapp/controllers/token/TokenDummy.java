package com.webapp.controllers.token;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TokenDummy implements Serializable {
	
	
	private String imgUri;
	private String userName;
	private String accessToken;
	
	public TokenDummy(String imgUri , String userName , String accessToken){
		this.setImgUri(imgUri);
		this.setUserName(userName);
		this.setAccessToken(accessToken);
	}

	public TokenDummy() {
		// TODO Auto-generated constructor stub
	}

	public String getImgUri() {
		return imgUri;
	}

	public void setImgUri(String imgUri) {
		this.imgUri = imgUri;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	

}
