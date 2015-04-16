package edu.sjsu.cmpe275.team6.SnippetShare.dao;

import edu.sjsu.cmpe275.team6.SnippetShare.model.Board;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Rucha on 4/15/15.
 */
public class JpaBoardDAO implements BoardDAO{
    private EntityManagerFactory entityManagerFactory;

    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean insert(Board board) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            manager.persist(board);
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
    public Board findByBoardId(int bid) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            Board board = manager.find(Board.class, bid);
            tx.commit();
            return board;
        } catch (RuntimeException e) {
            tx.rollback();
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public void update(Board board) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        Board board1 = manager.find(Board.class, board.getBid());
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            board.setTitle(board1.getTitle());
            board.setCategory(board1.getCategory());
            board.setIsPublic(board1.getIsPublic());
            board.setMembers(board1.getMembers());
            board.setRequestors(board1.getRequestors());
            board.setUpdatedAt(new Timestamp(System.currentTimeMillis())); //set the updated date to current time and date
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }

    }

    @Override
    public boolean delete(int bid) {
       EntityManager manager = entityManagerFactory.createEntityManager();
        Board board = manager.find(Board.class,bid);
        EntityTransaction tx = manager.getTransaction();
        try{
            tx.begin();
            if(board != null) {
                System.out.println(board.getBid() + ", " + board.getTitle() + ", " + board.getOwner());
                manager.refresh(board);//for cascading delete access and request
                manager.remove(board);
                tx.commit();
                return true;
            }
        else{
                tx.commit();
                return false; //board not found
            }
        }catch (RuntimeException e) {
        tx.rollback();
        throw e;
    } finally {
        manager.close();
    }


    }

    @Override
    public List<Board> allBoards() {
        String query = "SELECT b FROM Board b"; //select all row from the table
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            List<Board> listBoards = manager.createQuery(query).getResultList();
            tx.commit();
            return listBoards; //return empty list, list.size() == 0
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }
}
