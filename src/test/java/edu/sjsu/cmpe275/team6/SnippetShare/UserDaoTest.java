package edu.sjsu.cmpe275.team6.SnippetShare;

import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rucha on 5/1/15.
 */
public class UserDaoTest {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    @Test
    public void createUser() throws Exception{
        boolean created;
        User user = new User("Rucha123","rucha123","rucha@gmail.com123");
        created = userDAO.insert(user);
        assert(created);
    }

    @Test
    public void updateUser() throws Exception{
        boolean updated;
        User user = userDAO.findByUserId(6);
        user.setAboutMe("I am in SJSU");
        userDAO.update(user);
        String aboutMe = userDAO.findByUserId(6).getAboutMe();
        assert (aboutMe!=null);

    }

    @Test
    public void getUser() throws Exception{
        User user = userDAO.findByUserId(6);
        String name = "Rucha123";
        System.out.println("In Get Method::" + user.getUsername());
        String userName = user.getUsername();
        assert(name.equals(userName));
    }

    @Test
    public void deleteUser() throws Exception{
        boolean deleted = userDAO.delete(6);
        assert (!deleted);//System does not support delete
    }

    @Test
    public void getAllUsers() throws Exception{
        List<User> users = new ArrayList<User>();
        users = userDAO.allUsers();
        //assert (users.size()== 4); //this numer will change as we add users.
        assert (!users.isEmpty()); //we never delete any users to this list shud not be empty
    }

}
