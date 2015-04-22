package edu.sjsu.cmpe275.team6.SnippetShare.model;

/*
 * @author Rucha
 */
import javax.persistence.*;

@Entity
@Table(name ="tag")
public class Tag {

	@Id
	@TableGenerator(name="tab", initialValue=0, allocationSize=500)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="tab")
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
