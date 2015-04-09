package edu.sjsu.cmpe275.team6.SnippetShare.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@Column(name = "public")
	private boolean publicAccess;
	
	//One user can have many boards
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
	private User owner;
	
	public Board(int bid, String title, String category, boolean publicAccess) {
		
		this.title = title;
		this.category = category;
		this.publicAccess = publicAccess;
		
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

	public boolean isPublicAccess() {
		return publicAccess;
	}

	public void setPublicAccess(boolean publicAccess) {
		this.publicAccess = publicAccess;
	}

	public User getOwner(){
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	
	
	
}
