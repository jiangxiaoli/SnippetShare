package edu.sjsu.cmpe275.team6.SnippetShare;

import edu.sjsu.cmpe275.team6.SnippetShare.controller.UserController;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.JpaUserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by Rucha on 4/29/15.
 */
@RunWith(value = MockitoJUnitRunner.class)
public class UserControllerTest {

    @Test
    public void thisGetsUser(){

///Test1
        //Given
        UserDAO userDAO = mock(JpaUserDAO.class);
        final UserController userController = new UserController(userDAO);
        when(userDAO.findByUserId(1)).thenReturn(new User());

        //when
        ResponseEntity<String> u = userController.showUser(1);

        //then
        assert (u.getStatusCode().is2xxSuccessful());
        System.out.println(u.toString());

    }

    @Test
    public void thisUserDoesNOtExist(){
        //Given
        UserDAO userDAO = mock(JpaUserDAO.class);
        final UserController userController = new UserController(userDAO);
        when(userDAO.findByUserId(1)).thenReturn(null);

        //when
        ResponseEntity<String> u = userController.showUser(100);

        //then
        assert (u.getStatusCode().is4xxClientError());

    }

    @Test
    public void getAllUsers(){
        //Given
        UserDAO userDAO = mock(JpaUserDAO.class);
        ArrayList<User> users = new ArrayList<User>();
         final UserController userController = new UserController(userDAO);
        when(userDAO.allUsers()).thenReturn(users);


        //when
        ResponseEntity<String> u = userController.showUsers();

        //then
        assert (u.getStatusCode().is2xxSuccessful());
        System.out.println(users);
    }



}
