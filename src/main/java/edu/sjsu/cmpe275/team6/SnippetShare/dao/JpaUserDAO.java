package edu.sjsu.cmpe275.team6.SnippetShare.dao;

import edu.sjsu.cmpe275.team6.SnippetShare.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * Created by Corn on 4/6/15.
 */
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

    @Override
    public void update(User user) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        User user1 = manager.find(User.class, user.getUserid());
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            if(user.getUsername() != null) {
                user1.setUsername(user.getUsername());
            }
            if(user.getPwd() != null){
                user1.setPwd(user.getPwd());
            }
            if(user.getProfilepic() != null){
                user1.setProfilepic(user.getProfilepic());
            }
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
        EntityManager manager = entityManagerFactory.createEntityManager();
        User user = manager.find(User.class, userid);
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            if(user != null) {
                System.out.println(user.getUserid()+", "+ user.getUsername());
//                manager.refresh(user);//for cascading delete address
                manager.remove(user);

                //not delete opponents

                tx.commit();
                return true;
            } else {
                tx.commit();
                return false; //user not found
            }
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public List<User> allUsers() {
        String query = "SELECT u FROM User u"; //select all row from the table
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
