//package edu.sjsu.cmpe275.team6.SnippetShare;
//
//import edu.sjsu.cmpe275.team6.SnippetShare.controller.UserController;
//import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
//import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.http.ResponseEntity;
//
//import static org.mockito.Mockito.when;
//
//
///**
//* Created by Rucha on 4/29/15.
//*/
//@RunWith(value = MockitoJUnitRunner.class)
//public class UserControllerTest {
//
//    @Test
//    public void thisGetsUser(){
//
/////Test1
//        //Given
//        ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
//        UserDAO userDAO = (UserDAO) context.getBean("userDAO");
//        // UserDAO userDAO = mock(JpaUserDAO.class);
//
//         UserController userController;
//        when(userDAO.findByUserId(1)).thenReturn(new User());
//
//        //when
//        ResponseEntity<String> u = userController.showUser(1);
//
//        //then
//        assert (u.getStatusCode().is2xxSuccessful());
//        System.out.println(u.toString());
//
//    }
//
////    @Test @Ignore
////    public void thisUserDoesNOtExist(){
////        //Given
////        UserDAO userDAO = mock(JpaUserDAO.class);
////        final UserController userController = new UserController(userDAO);
////        when(userDAO.findByUserId(1)).thenReturn(null);
////
////        //when
////        ResponseEntity<String> u = userController.showUser(100);
////
////        //then
////        assert (u.getStatusCode().is4xxClientError());
////
////    }
////
////    @Test @Ignore
////    public void getAllUsers(){
////        //Given
////     //   UserDAO userDAO = mock(JpaUserDAO.class);
////        ArrayList<User> users = new ArrayList<User>();
////         final UserController userController = new UserController(userDAO);
////        when(userDAO.allUsers()).thenReturn(users);
////
////
////        //when
////        ResponseEntity<String> u = userController.showUsers();
////
////        //then
////        assert (u.getStatusCode().is2xxSuccessful());
////        System.out.println(users);
////    }
//
//
//
//}
