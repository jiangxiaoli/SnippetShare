package edu.sjsu.cmpe275.team6.SnippetShare.dao;

import edu.sjsu.cmpe275.team6.SnippetShare.model.Board;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;

import java.util.List;

/**
 * Created by Corn on 4/22/15.
 */
public interface RequestDAO {
    public void addRequest(Board board, User user);
    public void approveRequest(Board board, User user);
    public void denyRequest(Board board, User user);
    public void removeAccess(Board board, User user);
}
