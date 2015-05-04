package edu.sjsu.cmpe275.team6.SnippetShare.controller;

import com.auth0.jwt.JWTVerifier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.JpaUserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter.UserAdapter;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auth0.jwt.JWTSigner;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yeyulin on 5/3/2015.
 */
@Controller
public class AuthController {

    private static final String SECRET =  "my secret";
    private static JWTSigner signer = new JWTSigner(SECRET);

    ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");


    //Verify login info and set auth token cookie
    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> login(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password,
            HttpServletResponse response
    ) {

        User user = userDAO.findByEmail(email);

        //gson to build and map user class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(User.class, new UserAdapter()).create();
        if(user != null && user.getPwd().equals(password)){
            System.out.println("Show user details::" + user.getUsername());


            HashMap<String, Object> claims = new HashMap<String, Object>();
            claims.put("email", email);
            String expiry = String.valueOf(System.currentTimeMillis() + 1000*60*30);
            claims.put("expiry", expiry);
            String authToken = signer.sign(claims);

            Cookie cookie = new Cookie("token",authToken);
            cookie.setMaxAge(-1); // cookie never expire
            response.addCookie(cookie);

            String result = gson.toJson(user);

            return new ResponseEntity<String>(result, HttpStatus.OK);
        }else{
            //no user
            String result = new Gson().toJson("No user found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/logout", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> logout(
            @RequestParam(value = "email", required = true) String email,
            HttpServletResponse response
    ) {

        //gson to build and map user class
            HashMap<String, Object> claims = new HashMap<String, Object>();
            claims.put("email", email);
            String expiry = String.valueOf(System.currentTimeMillis());
            claims.put("expiry", expiry);
            String authToken = signer.sign(claims);

            Cookie cookie = new Cookie("token",authToken);
            cookie.setMaxAge(0); // expire cookie immediately, e.g delete the cookie.
            response.addCookie(cookie);

            System.out.println("User logout: " + email);
            String result = new Gson().toJson("logout success");
            return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}
