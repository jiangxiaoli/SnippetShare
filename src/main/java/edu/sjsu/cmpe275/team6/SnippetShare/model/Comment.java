package edu.sjsu.cmpe275.team6.SnippetShare.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
	
	@Id
	@TableGenerator(name="tab", initialValue=0, allocationSize=500)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
	@Column(name = "cid")
	private int cid;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "time")
	private Date time;
	
	//One User can make multiple comments
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private User user;
		
		
	//One Snippet can have multiple comments
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sid")
	private Snippet snippet;
	
	public Comment(String content) {

		this.content = content;
	}

	public int getCid() {
		return cid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Snippet getSnippet() {
		return snippet;
	}

	public void setSnippet(Snippet snippet) {
		this.snippet = snippet;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	
	
}
