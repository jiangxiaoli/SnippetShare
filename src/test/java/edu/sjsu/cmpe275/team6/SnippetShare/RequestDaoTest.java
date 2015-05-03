package edu.sjsu.cmpe275.team6.SnippetShare;

import edu.sjsu.cmpe275.team6.SnippetShare.dao.BoardDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.RequestDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Board;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rucha on 5/1/15.
 */
public class RequestDaoTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    RequestDAO requestDAO = (RequestDAO) context.getBean("requestDAO");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    BoardDAO boardDAO = (BoardDAO) context.getBean("boardDAO");



    @Test
    public void addRequest(){
        List<User> requestors = new ArrayList<User>();
        User user = userDAO.findByUserId(1);
        Board board = boardDAO.findByBoardId(4);
        requestDAO.addRequest(board,user);
        requestors = board.getRequestors();
        System.out.println("requestors::" + requestors);
        assert (!requestors.isEmpty());
    }

    @Test
    public void approveRequest(){
        User user = userDAO.findByUserId(1);
        Board board = boardDAO.findByBoardId(4);
        requestDAO.approveRequest(board,user);
        List<User> members = new ArrayList<User>();
        members = board.getMembers();
        System.out.println("Members::" + members);
        assert (!members.isEmpty());
    }

    @Test
    public void removeAccess(){
        Board board = boardDAO.findByBoardId(4);
        User user = userDAO.findByUserId(1);
        requestDAO.removeAccess(board,user);
        List<User> members = new ArrayList<User>();
        members = board.getMembers();
        assert (!members.contains(user.getUserid()));
    }

    @Test
    public void denyAccess(){
        User user = userDAO.findByUserId(2);
        Board board = boardDAO.findByBoardId(4);
        requestDAO.addRequest(board,user);
        System.out.println("Sent Request...");
        requestDAO.denyRequest(board,user);
        System.out.println("Access denied..");
        List<User> members = new ArrayList<User>();
        members = board.getMembers();
        assert (!members.contains(user.getUserid()));

    }
}
