package edu.sjsu.cmpe275.team6.SnippetShare.model;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "snippet")
public class Snippet {

	@Id
	@TableGenerator(name="tab", initialValue=0, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
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
	private long createdAt;

	@Column(name = "updatedAt")
	private long updatedAt;

	//One user can have many snippets
    @ManyToOne(fetch = FetchType.LAZY,cascade= CascadeType.REMOVE)
    @JoinColumn(name = "author")
    private User author;

   //One board can have many snippets
	@ManyToOne(fetch = FetchType.LAZY,cascade= CascadeType.REMOVE)
	@JoinColumn(name = "bid")
    private Board board;

    @Column(name = "comments")
    @OneToMany(mappedBy = "snippet")
    private List<Comment> comments;

	public Snippet(String title, String content) {
		this.title = title;
		this.content = content;
	}

	public Snippet() {}
	
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

	public long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(long updatedAt) {
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

    public int getNumberOfComments(){
        return this.comments.size();
    }

	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) { this.comments = comments; }
}
