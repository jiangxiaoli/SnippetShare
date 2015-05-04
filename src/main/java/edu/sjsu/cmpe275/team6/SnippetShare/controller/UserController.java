/**
 * Created by yeyulin on 4/14/2015.
 */
package edu.sjsu.cmpe275.team6.SnippetShare.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter.UserAdapter;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
   ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");
//added while writing the jUnit test cases
//    UserDAO userDAO;
//

//   @Autowired
//   public UserController(UserDAO userDAO){
//        this.userDAO = userDAO;
//    }
    //1. POST Create a user
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createUser(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "pwd", required = true) String pwd,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "userAvatarId", required = false) String userAvatarId,
            @RequestParam(value = "aboutMe", required = false) String aboutMe) {
        User user = new User(username, pwd, email);
        System.out.println("In Controller,Avatar Id::" + user.getUserAvatarId());
        if (userAvatarId == null) user.setUserAvatarId("1");
        else user.setUserAvatarId(userAvatarId);
        user.setAboutMe(aboutMe);

        Gson gson = new Gson();
        try {
            userDAO.insert(user);
            return new ResponseEntity<String>(gson.toJson(user), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("fail to create user");
            e.printStackTrace();
            return new ResponseEntity<String>(gson.toJson("userCreationError"), HttpStatus.BAD_REQUEST);
        }

    }

    //2. GET get a user
    @RequestMapping(value = "/{userid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> showUser(@PathVariable int userid) {
        User user = userDAO.findByUserId(userid);

        //gson to build and map user class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(User.class, new UserAdapter()).create();

        if (user != null) {
            System.out.println("Show user details::" + user.getUsername());
            String result = gson.toJson(user);
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } else {
            //user not found
            String result = new Gson().toJson("User-" + userid + " not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }

    //3.Update the User
    @RequestMapping(value = "/{userid}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> updateUser(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "pwd", required = false) String pwd,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "userAvatarId", required = false) String userAvatarId,
            @RequestParam(value = "aboutMe", required = false) String aboutMe,
            @PathVariable int userid) {

        User user = userDAO.findByUserId(userid);
        if(user != null) {
            user.setUsername(username);
            user.setPwd(pwd);
            user.setEmail(email);
            user.setUserAvatarId(userAvatarId);
            user.setAboutMe(aboutMe);

            System.out.println("User-" + user.getUsername());

            //gson to build and map user class
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.registerTypeAdapter(User.class, new UserAdapter()).create();

            try {
                userDAO.update(user);
                System.out.println("User-"+userid+" Updated!!");
                user = userDAO.findByUserId(userid);
                String result = gson.toJson(user);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            } catch (Exception e){
                String result = new Gson().toJson("Fail to update User-" + userid);
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }

        } else {
            String result = new Gson().toJson("User-" + userid + " not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }

    }

    //4. GET get all users
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> showUsers() {
        List<User> users = userDAO.allUsers();

        //gson to build and map user class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(User.class, new UserAdapter()).create();

        if(users != null){

            for(User user: users) {
                System.out.println("Show user details::" + user.getUsername());

            }

            String result = gson.toJson(users);

            return new ResponseEntity<String>(result, HttpStatus.OK);
        }else{
            //no user
            String result = new Gson().toJson("No user found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }


}

