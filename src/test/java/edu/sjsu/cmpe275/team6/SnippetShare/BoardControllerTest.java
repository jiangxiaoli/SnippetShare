//package edu.sjsu.cmpe275.team6.SnippetShare;
//
//import edu.sjsu.cmpe275.team6.SnippetShare.controller.BoardController;
//import edu.sjsu.cmpe275.team6.SnippetShare.dao.BoardDAO;
//import edu.sjsu.cmpe275.team6.SnippetShare.dao.JpaBoardDAO;
//import edu.sjsu.cmpe275.team6.SnippetShare.dao.JpaUserDAO;
//import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
//import edu.sjsu.cmpe275.team6.SnippetShare.model.Board;
//import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//
///**
// * Created by Rucha on 4/29/15.
// */
//@RunWith(MockitoJUnitRunner.class)
//public class BoardControllerTest {
//
//    @Test
//    public void getUserBoards(){
//        ///Test1
//        //Given
//        UserDAO userDAO = mock(JpaUserDAO.class);
//        User u = userDAO.findByUserId(1);
//        BoardDAO boardDAO = mock(JpaBoardDAO.class);
//        List<Board> boards = new ArrayList<Board>();
//        final BoardController boardController = new BoardController(userDAO,boardDAO);
//        when(boardDAO.allBoards(1)).thenReturn(boards);
//
//
//        //when
//        ResponseEntity<String> u1 = boardController.showBoards(1);
//
//        //then
//        assert (u1.getStatusCode().is2xxSuccessful());
//    }
//
//    @Test
//
//    public void getUserDoesNotExistBoards(){
//        //Given
//        UserDAO userDAO = mock(JpaUserDAO.class);
//        BoardDAO boardDAO = mock(JpaBoardDAO.class);
//        final BoardController boardController = new BoardController(userDAO,boardDAO);
//        when(boardDAO.findByBoardId(100)).thenReturn(null);
//
//        //when
//        ResponseEntity<String> u = boardController.showBoard(1,100);
//
//        //then
//        assert (u.getStatusCode().is4xxClientError());
//
//    }
//
//}
//
//
