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
@Table(name = "request")
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="rid")
	private int rid;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid")
	private User owner;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bid")
	private Board board;


	public int getRid() {
		return rid;
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
