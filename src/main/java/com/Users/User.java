package com.Users;

import java.util.ArrayList;
import java.util.List;

public class User {
		
	private String userName;
	private String passWord;
	private boolean isLoggedIn;
	private int sessionId;
	private List<String> messages = new ArrayList<String>();
	
	public User(String userName, String passWord, boolean isLoggedIn, int sessionId, List<String> messages) {
		this.userName = userName;
		this.passWord = passWord;
		this.isLoggedIn = isLoggedIn;
		this.sessionId = sessionId;
		this.messages = messages;
	}
	
	public User() {
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	public int getSessionId() {
		return sessionId;
	}
	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	


}
