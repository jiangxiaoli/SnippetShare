package edu.sjsu.cmpe275.team6.SnippetShare.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Corn on 4/6/15.
 * @author Rucha
 */
@Entity
@Table(name = "user")
public class User {
	@Id
	@TableGenerator(name="tab", initialValue=0, allocationSize=500)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
    @Column(name = "userid")
	private int userid;
	
	
	@Column(name ="username",nullable = false,unique = true)
	private String username;
	
	@Column(name ="pwd",nullable = false)
	private String pwd;
	
	@Column(name ="email",nullable = false,unique = true)
	private String email;
	
	@Column(name ="userAvatarId")
	private String userAvatarId;

    @Column(name = "aboutMe")
    private String aboutMe;

//    @Column(name = "snippet")
//    @OneToMany(mappedBy = "author")
//    private ArrayList<Snippet> snippets;
//
   @Column(name = "board")
    @OneToMany(mappedBy = "owner")
    private List<Board> boards;

	public User(String username, String pwd, String email) {
		this.username = username;
		this.pwd = pwd;
		this.email = email;

	}
//added in order to work the findByUserId
    public User(){}

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
	
	public String getUserAvatarId() {
		return userAvatarId;
	}

	public void setUserAvatarId(String userAvatarId) {
		this.userAvatarId = userAvatarId;
	}

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    //    public ArrayList<Snippet> getSnippets() {
//        return snippets;
//    }
//
//    public void setSnippets(ArrayList<Snippet> snippets) {
//        this.snippets = snippets;
//    }
//
    public List<Board> getBoards() {
        return boards;
    }

    public void setBoards(List<Board> boards) {
        this.boards = boards;
    }

    public String toString(){
      return (this.getUserid() + "," + this.getUsername() + "," + this.getEmail()+","+this.getBoards());
    }
}
