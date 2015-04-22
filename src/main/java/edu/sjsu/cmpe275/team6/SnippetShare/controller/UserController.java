/**
 * Created by yeyulin on 4/14/2015.
 */
package edu.sjsu.cmpe275.team6.SnippetShare.controller;

import com.google.gson.Gson;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");

    //1. POST Create a user
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createUser(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "pwd", required = true) String pwd,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "userAvatarId", required = false) String userAvatarId,
            @RequestParam(value = "aboutMe", required = false) String aboutMe,
            ModelMap model) {
        User user = new User(username, pwd, email);
        System.out.println("In Controller,Avatar Id::" + user.getUserAvatarId());
        if (userAvatarId == null) user.setUserAvatarId("1");
        else
            user.setUserAvatarId(userAvatarId);
        user.setAboutMe(aboutMe);

        Gson gson = new Gson();
        try {
            userDAO.insert(user);

            return new ResponseEntity<String>(gson.toJson(user), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("fail to create user");
            return new ResponseEntity<String>(gson.toJson("userCreationError"), HttpStatus.BAD_REQUEST);
        }

    }

    //2. GET get a user
    @RequestMapping(value = "/{userid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> showPlayer(@PathVariable int userid) {
        User user = userDAO.findByUserId(userid);
        System.out.println("I am here");
        System.out.println(user.toString());

        //gson to build and map user class
        Gson gson = new Gson();
        // GsonBuilder gsonBuilder = new GsonBuilder();
        //  Gson gson = gsonBuilder.registerTypeAdapter(User.class, new UserAdapter()).create();

        if (user != null) {
            System.out.println("Show user details::" + user.getUserid());
            String result = gson.toJson(user);
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } else {
            //user not found
            String result = gson.toJson("User-" + userid + " not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }

    //3.Update the User

    @RequestMapping(value = "/{userid}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> updateUser(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "pwd", required = true) String pwd,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "userAvatarId", required = false) String userAvatarId,
            @RequestParam(value = "aboutMe", required = false) String aboutMe,
            @PathVariable int userid) {

        User user = userDAO.findByUserId(userid);
        user.setUsername(username);
        user.setPwd(pwd);
        user.setEmail(email);
        user.setUserAvatarId(userAvatarId);
        user.setAboutMe(aboutMe);

        Gson gson = new Gson();
        try {
            if(user!=null) {
                userDAO.update(user);
                String result = gson.toJson(user);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            }
            else {
                //user not found
                String result = gson.toJson("User-" + userid + " not found");
                return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("fail to update user");
            return new ResponseEntity<String>(gson.toJson("userUpdateError"), HttpStatus.BAD_REQUEST);
        }


    }
}

