package edu.sjsu.cmpe275.team6.SnippetShare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;



/**
 * Created by Corn on 4/6/15.
 * @author Rucha
 */
@Entity
@Table(name = "user",uniqueConstraints={@UniqueConstraint(columnNames={"username","pwd","email"})})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
	private int userid;
	
	
	@Column(name ="username",nullable = false)
	private String username;
	
	@Column(name ="pwd",nullable = false)
	private String pwd;
	
	@Column(name ="email",nullable = false)
	private String email;
	
	@Column(name ="profilepic")
	private String profilepic;

	public User(String username, String pwd, String email,String profilepic) {
		this.username = username;
		this.pwd = pwd;
		this.email = email;
		this.profilepic = profilepic;
	}

	public int getUserid() {
		return userid;
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
	
	public String getProfilepic() {
		return profilepic;
	}

	public void setProfilepic(String profilepic) {
		this.profilepic = profilepic;
	}

}
