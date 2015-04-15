package edu.sjsu.cmpe275.team6.SnippetShare.model;




import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "snippet")
public class Snippet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "sid")
	private int sid;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "url")
	private String url;
	
	@Column(name = "language")
	private String language;
	
	//can we have created time and updated time??
	@Column(name = "createdAt")
	private Timestamp createdAt;
	
	@Column(name = "updatedAt")
	private Timestamp updatedAt;
	
	//One user can have many snippets
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private User author;
		
   //One board can have many snippets
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bid")
    private Board board;
	
	public Snippet(String title, String content, String url,Timestamp createdAt,Timestamp updatedAt) {
		this.title = title;
		this.content = content;
		this.url = url;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getSid() {
		return sid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
}
