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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");

    //1. POST Create a user
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createUser(
            @RequestParam(value="username", required = true) String username,
            @RequestParam(value="pwd", required = true) String pwd,
            @RequestParam(value="email", required = true) String email,
            @RequestParam(value="profilepic", required = false) String profilepic,

            ModelMap model) {

        User user = new User(username, pwd, email, profilepic);
        Gson gson = new Gson();

        try {
            userDAO.insert(user);
            return new ResponseEntity<String>(gson.toJson(user), HttpStatus.OK);
        } catch(Exception e) {
            System.out.println("fail to create player");
            return new ResponseEntity<String>(gson.toJson("userCreationError"), HttpStatus.BAD_REQUEST);
        }

    }


}

