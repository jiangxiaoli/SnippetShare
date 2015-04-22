package edu.sjsu.cmpe275.team6.SnippetShare.security;

import edu.sjsu.cmpe275.team6.SnippetShare.dao.JpaUserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
        JpaUserDAO userDAO = (JpaUserDAO) context.getBean("userDAO");
        edu.sjsu.cmpe275.team6.SnippetShare.model.User user = userDAO.findByEmail(email);

        if (user != null) {
            ArrayList<GrantedAuthority> authorizationArray = new ArrayList<GrantedAuthority> ();
            authorizationArray.add(new GrantedAuthorityImpl("USER_ROLE"));
            return new org.springframework.security.core.userdetails.User(email, user.getPwd(), true, true, true, true,
                    authorizationArray
            );
        }

        return null;
}
}
