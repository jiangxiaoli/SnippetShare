package edu.sjsu.cmpe275.team6.SnippetShare.model;

import javax.persistence.*;
import java.util.ArrayList;


/**
 * Created by Corn on 4/6/15.
 * @author Rucha
 */
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "userid")
	private int userid;
	
	
	@Column(name ="username",nullable = false,unique = true)
	private String username;
	
	@Column(name ="pwd",nullable = false)
	private String pwd;
	
	@Column(name ="email",nullable = false,unique = true)
	private String email;
	
	@Column(name ="profilepic")
	private String profilepic;

    @Column(name = "snippet")
    @OneToMany(mappedBy = "author")
    private ArrayList<Snippet> snippets;

    @Column(name = "board")
    @OneToMany(mappedBy = "owner")
    private ArrayList<Board> boards;

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

    public ArrayList<Snippet> getSnippets() {
        return snippets;
    }

    public void setSnippets(ArrayList<Snippet> snippets) {
        this.snippets = snippets;
    }

    public ArrayList<Board> getBoards() {
        return boards;
    }

    public void setBoards(ArrayList<Board> boards) {
        this.boards = boards;
    }
}
