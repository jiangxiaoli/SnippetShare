package edu.sjsu.cmpe275.team6.SnippetShare.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/*
 * @author Rucha
 */

@Entity
@Table(name="board")
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bid")
	private int bid;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "isPublic")
	private boolean isPublic;


	
	//One user can have many boards
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
	private User owner;
	
	@Column(name = "createdAt")
	private Timestamp createdAt;
	
	@Column(name = "updatedAt")
	private Timestamp updatedAt;

    //when board is deleted delete the access and requests related with the baord,cascadeType = remove
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="access",
			joinColumns={@JoinColumn(name="bid", referencedColumnName="bid")},
			inverseJoinColumns={@JoinColumn(name="uid", referencedColumnName="userid")})
	private List<User> members;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			name="request",
			joinColumns={@JoinColumn(name="bid", referencedColumnName="bid")},
			inverseJoinColumns={@JoinColumn(name="uid", referencedColumnName="userid")})
	private List<User> requestors;

//    @Column(name = "snippets")
//    @OneToMany(mappedBy = "board")
//    private ArrayList<Snippet> snippets;
	
	public Board(String title, String category, boolean isPublic,
			Timestamp createdAt, Timestamp updatedAt) {
		
		this.title = title;
		this.category = category;
		this.isPublic = isPublic;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}



	public int getBid() {
		return bid;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public User getOwner(){
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
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

	public List<User> getMembers() {
		return members;
	}

	public void setMembers(List<User> members) {
		this.members = members;
	}

	public List<User> getRequestors() {
		return requestors;
	}

	public void setRequestors(List<User> requestors) {
		this.requestors = requestors;
	}

    public int getNumberOfRequests(){
        return requestors.size();
    }

//    public int getNumberOfSnippets(){
//        return snippets.size();
//    }
	

	
}
