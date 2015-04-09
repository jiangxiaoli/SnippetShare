package edu.sjsu.cmpe275.team6.SnippetShare.model;

/*
 * @author Rucha
 */
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
@Table(name ="tag")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tid")
	private int tid;
	
	@Column(name = "content")
	private String content;
	
	//One Snippet can have many Tags
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sid")
    private Snippet snippet;
	
	public Tag(String content) {
	
		this.content = content;
	}

	public int getTid() {
		return tid;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Snippet getSnippet() {
		return snippet;
	}

	public void setSnippet(Snippet snippet) {
		this.snippet = snippet;
	}

	
}
