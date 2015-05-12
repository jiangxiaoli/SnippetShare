package edu.sjsu.cmpe275.team6.SnippetShare.controller;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yeyulin on 5/3/2015.
 */
@Controller
public class AuthController {

    private static final String SECRET =  "my secret";
    private static JWTSigner signer = new JWTSigner(SECRET);
    private static JWTVerifier verifier = new JWTVerifier(SECRET);

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

    //Restore user token
    @RequestMapping(value="/restore", method=RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> restore(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("interceptor invoked");

        Cookie[] cookies = request.getCookies();
        Cookie authCookie = null;

        if (cookies != null) {
            for(int i = 0; i < cookies.length; i++){
                Cookie c =  cookies[i];
                System.out.println("Get cookie: Name=" + c.getName() + ", Value=" + c.getValue() + ", Comment=" + c.getComment()
                        + ", Domain=" + c.getDomain() + ", MaxAge=" + c.getMaxAge() + ", Path=" + c.getPath()
                        + ", Version=" + c.getVersion());
                if (c.getName().equals("token")) {
                    authCookie = c;
                }
            }
            if (authCookie == null) {
                System.err.println("Auth cookie does not exist!");
                //no user
                String result = new Gson().toJson("Auth cookie does not exist!");
                return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
            } else {
                String token = authCookie.getValue();
                try {
                    Map<String, Object> decoded = verifier.verify(token);
                    System.out.println("Auth token is verified. Email:"+decoded.get("email"));

                    if(Long.parseLong((String) decoded.get("expiry"), 10) < new Long(System.currentTimeMillis())) {
                        System.err.println("Auth token expired");
                        //no user
                        String result = new Gson().toJson("Auth token expired");
                        return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
                    }

                    System.out.println("Auth token expiration check OK");
                    User user = userDAO.findByEmail((String)decoded.get("email"));

                    if(user != null) {
                        System.out.println("User is verified. ");
                        //gson to build and map user class
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        Gson gson = gsonBuilder.registerTypeAdapter(User.class, new UserAdapter()).create();
                        String result = gson.toJson(user);
                        return new ResponseEntity<String>(result, HttpStatus.OK);
                    } else {
                        System.err.println("Invalid user!");
                        String result = new Gson().toJson("Invalid user");
                        return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
                    }

                } catch (SignatureException signatureException) {
                    System.err.println("Invalid signature!");
                    String result = new Gson().toJson("Invalid signature");
                    return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
                } catch (IllegalStateException illegalStateException) {
                    System.err.println("Invalid Token! " + illegalStateException);
                    String result = new Gson().toJson("Invalid Token");
                    return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (JWTVerifyException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        System.err.println("No cookies at all!");
        String result = new Gson().toJson("No cookies at all");
        return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
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
