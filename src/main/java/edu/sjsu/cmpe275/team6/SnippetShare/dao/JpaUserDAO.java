package edu.sjsu.cmpe275.team6.SnippetShare.dao;

import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Corn on 4/6/15.
 */
@Component
public class JpaUserDAO implements UserDAO {
    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean insert(User user) {
        //handle email unique exception
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            manager.persist(user);
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public User findByUserId(int userid) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            User user = manager.find(User.class, userid);
            tx.commit();
            return user;
        } catch (RuntimeException e) {
            tx.rollback();
            return null;
        } finally {
            manager.close();
        }
    }

    public User findByEmail(String email) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();

            TypedQuery<User> q = manager.createQuery("SELECT u FROM user u", User.class);
            User user = q.getSingleResult();
            tx.commit();
            return user;
        } catch (RuntimeException e) {
            tx.rollback();
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public void update(User user) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        User user1 = manager.find(User.class, user.getUserid());
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            if(user.getUsername() != null){
                user1.setUsername(user.getUsername());
            }
            if(user.getEmail() != null) {
                user1.setEmail(user.getEmail());
            }
            if(user.getPwd()!= null){
                user1.setPwd(user.getPwd());
            }
            if(user.getUserAvatarId() != null) {
                user1.setUserAvatarId(user.getUserAvatarId());
            }
            if(user.getAboutMe() != null){
                user1.setAboutMe(user.getAboutMe());
            }

            //  user1.setBoards(user.getBoards());
            //user1.setSnippets(user.getSnippets());
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public boolean delete(int userid) {
//        delete user is not supported
        return false;
    }

    @Override
    public List<User> allUsers() {
        String query = "SELECT u FROM User as u"; //select all row from the table
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            List<User> listUsers = manager.createQuery(query).getResultList();
            tx.commit();
            return listUsers; //return empty list, list.size() == 0
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }

    }
}
