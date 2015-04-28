package edu.sjsu.cmpe275.team6.SnippetShare.dao;

import java.util.List;

import edu.sjsu.cmpe275.team6.SnippetShare.model.Comment;

public interface CommentDAO {

	public boolean insert(Comment comment);
    public Comment findByCommentId(int cid);
    public boolean delete(int cid);
    public List<Comment> allComments(int sid);
	
}
