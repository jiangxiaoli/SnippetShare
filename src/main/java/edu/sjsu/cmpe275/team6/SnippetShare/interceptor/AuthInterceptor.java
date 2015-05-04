package edu.sjsu.cmpe275.team6.SnippetShare.interceptor;


import com.auth0.jwt.JWTVerifier;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.JpaUserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.SignatureException;
import java.util.Map;

/**
 * Created by yeyulin on 4/28/2015.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {

    private static final String SECRET =  "my secret";
    private static JWTVerifier verifier = new JWTVerifier(SECRET);
    JpaUserDAO userDAO;

    @Autowired
    public AuthInterceptor(JpaUserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return false;
            } else {
                String token = authCookie.getValue();
                try {
                    Map<String, Object> decoded = verifier.verify(token);
                    System.out.println("Auth token is verified. Email:"+decoded.get("email"));

                    User user = userDAO.findByEmail((String)decoded.get("email"));

                    if(user != null) {
                        System.out.println("User is verified. Set request user and pass the execution to the handler.");
                        request.setAttribute("user", user);
                        authCookie.setMaxAge(60*30);

                        return true;
                    } else {
                        System.err.println("Invalid user!");
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                        return false;
                    }

                } catch (SignatureException signatureException) {
                    System.err.println("Invalid signature!");
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                } catch (IllegalStateException illegalStateException) {
                    System.err.println("Invalid Token! " + illegalStateException);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                }

            }
        } else {
            System.err.println("No cookies at all!");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }
}
