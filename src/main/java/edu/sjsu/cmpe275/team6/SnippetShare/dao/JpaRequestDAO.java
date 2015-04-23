package edu.sjsu.cmpe275.team6.SnippetShare.dao;

import edu.sjsu.cmpe275.team6.SnippetShare.model.Board;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
 * Created by Corn on 4/22/15.
 */
public class JpaRequestDAO implements RequestDAO {

    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public void addRequest(Board board, User user) {
        //check dup
        for(User requestor: board.getRequestors()){
            if(requestor.getUserid() == user.getUserid()) return;
        }

        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        Board board1 = manager.find(Board.class, board.getBid());
        User user1 = manager.find(User.class, user.getUserid());

        //check if null
        if(board == null || user1 == null) return;

        try {
            tx.begin();
            board1.getRequestors().add(user1);
            tx.commit();
            return;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }

    }

    @Override
    public void approveRequest(Board board, User user) {

        //check if user is in requestors
        boolean hasUser = false;
        for(User requestor: board.getRequestors()){
            if(requestor.getUserid() == user.getUserid()) hasUser = true;
        }
        if(!hasUser) return;

        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        Board board1 = manager.find(Board.class, board.getBid());
        User user1 = manager.find(User.class, user.getUserid());

        //check if null
        if(board == null || user1 == null) return;

        try {
            tx.begin();
            board1.getRequestors().remove(user1);
            board1.getMembers().add(user1);
            tx.commit();
            return;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }

    }

    @Override
    public void denyRequest(Board board, User user) {
        //check if user is in requestors
        boolean hasUser = false;
        for(User requestor: board.getRequestors()){
            if(requestor.getUserid() == user.getUserid()) hasUser = true;
        }
        if(!hasUser) return;

        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        Board board1 = manager.find(Board.class, board.getBid());
        User user1 = manager.find(User.class, user.getUserid());

        //check if null
        if(board == null || user1 == null) return;

        try {
            tx.begin();
            board1.getRequestors().remove(user1);
            tx.commit();
            return;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public void removeAccess(Board board, User user) {
        //check if user is in members
        boolean hasUser = false;
        for(User requestor: board.getMembers()){
            if(requestor.getUserid() == user.getUserid()) hasUser = true;
        }
        if(!hasUser) return;

        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        Board board1 = manager.find(Board.class, board.getBid());
        User user1 = manager.find(User.class, user.getUserid());

        //check if null
        if(board == null || user1 == null) return;

        try {
            tx.begin();
            board1.getMembers().remove(user1);
            tx.commit();
            return;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }
}
