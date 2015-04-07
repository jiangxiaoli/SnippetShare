package edu.sjsu.cmpe275.team6.SnippetShare.model;

/**
 * Created by Corn on 4/6/15.
 * 
userid (primary key, unique, auto increment)
username (unique)
pwd
email (unique)
profilepic

 */

public class User {
	
	private String userid;
	private String username;
	private String pwd;
	private String email;
	
	public User(String userid, String username, String pwd, String email) {
		super();
		this.userid = userid;
		this.username = username;
		this.pwd = pwd;
		this.email = email;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
}
