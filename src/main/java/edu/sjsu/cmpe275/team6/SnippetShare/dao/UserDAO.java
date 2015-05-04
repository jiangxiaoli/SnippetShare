package edu.sjsu.cmpe275.team6.SnippetShare.dao;

import edu.sjsu.cmpe275.team6.SnippetShare.model.User;

import java.util.List;

/**
 * Created by Corn on 4/6/15.
 */
public interface UserDAO {
    public boolean insert(User user);
    public User findByUserId(int userid);
    public User findByEmail(String email);
    public void update(User user);
    public boolean delete(int userid);
    public List<User> allUsers();
}
