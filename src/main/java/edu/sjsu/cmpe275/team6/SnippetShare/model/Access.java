/**
 * 
 */
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

/**
 * @author Rucha
 *
 */
@Entity
@Table(name = "access")
public class Access {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="aid" )
	private int aid;
	
	
    //One user can have multiple access id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uid")
	private User owner;
	
	//One board to can have multiple access
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bid")
	private Board board;

	public Access(User owner, Board board) {
		this.owner = owner;
		this.board = board;
	}

	public int getAid() {
		return aid;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}
  
	
	


}
