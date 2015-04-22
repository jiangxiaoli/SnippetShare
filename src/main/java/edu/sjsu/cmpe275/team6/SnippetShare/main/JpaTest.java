package edu.sjsu.cmpe275.team6.SnippetShare.main;

import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Corn on 4/6/15.
 */
public class JpaTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
        UserDAO userDAO = (UserDAO) context.getBean("userDAO");

                    /**********User DAO test*********/
        User user = new User("Rucha","test1","rucha.b@gmail.com");
        userDAO.insert(user);
        User user1 = new User("Abc","test1","test1@gmail.com");
        userDAO.insert(user1);
        User u = userDAO.findByUserId(12);
        if(u!=null)
        System.out.println(u.toString());
       else
        System.out.println("User not found");
        if(u != null){
            u.setEmail("test2");
            userDAO.update(u);
            System.out.println(u.toString());
        }
        else{
            System.out.println("User does not exist");

        }

        List<User> allUsers  = userDAO.allUsers();
        for(User u2 : allUsers) System.out.println(u2.toString());

        userDAO.delete(12);
            System.out.println("Deleted the user");
        List<User> allUsers2  = userDAO.allUsers();
        for(User u2 : allUsers2) {
            System.out.println(u2.toString());
        }

        User u4 = userDAO.findByUserId(22);
        if(u4!=null)
            System.out.println(u4.toString());
        else
            System.out.println("User not found");
        /**************Board DAO test**************/

    }
}
