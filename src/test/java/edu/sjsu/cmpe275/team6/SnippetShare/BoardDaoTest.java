package edu.sjsu.cmpe275.team6.SnippetShare;

import edu.sjsu.cmpe275.team6.SnippetShare.dao.BoardDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Board;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Rucha on 5/1/15.
 */
public class BoardDaoTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    BoardDAO boardDAO = (BoardDAO) context.getBean("boardDAO");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");


    @Test
    public void createBoard(){
        boolean created;
        Board board = new Board("testBoard","cat1",true);
        created = boardDAO.insert(board);
        assert (created);
    }

    @Test
    public void updateBoard(){
        Board board = boardDAO.findByBoardId(4);
        board.setDescription("test description");
        boardDAO.update(board);
        assert(board.getDescription().equals("test description"));
    }

    @Test
    public void getBoard(){
        Board board = boardDAO.findByBoardId(4);
        assert (board.getTitle().equals("testBoard"));
    }

    @Test
    public void getAllBoards(){
        List<Board> boards1  = boardDAO.allBoards(1);
        List<Board> boards2  = boardDAO.allBoards(6);
        assert (boards1.size()==2);
        assert (boards2.isEmpty()); //no baords added for user id 6
    }

    @Test
    public void deleteBoard(){
        Board deletetest = new Board("deleteBoard" ,"cat1" ,false);
        boardDAO.insert(deletetest);
        int id = deletetest.getBid();
        boolean deleted = boardDAO.delete(id);
        assert (deleted);
        assert (boardDAO.findByBoardId(id)==null);
    }

}
