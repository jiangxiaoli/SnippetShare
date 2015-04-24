package edu.sjsu.cmpe275.team6.SnippetShare.dao;

import edu.sjsu.cmpe275.team6.SnippetShare.model.Board;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Snippet;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Rucha on 4/16/15.
 */
public class JpaSnippetDAO implements SnippetDAO {

    private EntityManagerFactory entityManagerFactory;
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public boolean insert(Snippet snippet) {
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        java.util.Date date= new java.util.Date();
        snippet.setCreatedAt(date.getTime());
        snippet.setUpdatedAt(date.getTime());
        try {
            tx.begin();
            manager.persist(snippet);
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
    public Snippet findBySnippetId(int sid) {
            EntityManager manager = entityManagerFactory.createEntityManager();
            EntityTransaction tx = manager.getTransaction();
            try {
                tx.begin();
                Snippet snippet = manager.find(Snippet.class, sid);
                tx.commit();
                return snippet;
            } catch (RuntimeException e) {
                tx.rollback();
                throw e;
            } finally {
                manager.close();
            }
    }

    @Override
    public void update(Snippet snippet) {
    //only the author of the snippet can edit the snippet will have to add this check
    // in contoller since user will be there thru path variable
        EntityManager manager = entityManagerFactory.createEntityManager();
        Snippet snippet1 = manager.find(Snippet.class, snippet.getSid());
        EntityTransaction tx = manager.getTransaction();
        
        try {
            tx.begin();
            if(snippet.getBoard()!=null)
            	snippet1.setBoard(snippet.getBoard());
            if(snippet.getContent()!=null)
            	snippet1.setContent(snippet.getContent());
            if(snippet.getAuthor()!=null)
            	snippet1.setAuthor(snippet.getAuthor());
            if(snippet.getLanguage()!=null)
            	snippet1.setLanguage(snippet.getLanguage());
            if(snippet.getTitle()!=null)
            	snippet1.setTitle(snippet.getTitle());
            if(snippet.getUrl()!=null)
            	snippet1.setUrl(snippet.getUrl());
            java.util.Date date= new java.util.Date();
            snippet1.setUpdatedAt(date.getTime());
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public boolean delete(int sid) {
      //Only the owner of the board can delete a snippet from board
      //this check will be added in the controller side
        EntityManager manager = entityManagerFactory.createEntityManager();
        Snippet snippet = manager.find(Snippet.class,sid);
        EntityTransaction tx = manager.getTransaction();
        
        try{
            tx.begin();
            if(snippet!=null){
                System.out.println(snippet.getSid()+", "+snippet.getTitle()+", "+ snippet.getAuthor());
                //manager.refresh(snippet); //for cascade delete
                manager.remove(snippet);
                tx.commit();
                return true;
            }
            else{
                tx.commit();
                return false;
            }
        }catch(RuntimeException e){
            tx.rollback();
            throw e;
        }finally{
            manager.close();
        }


    }

    @Override
    public List<Snippet> allSnippets(int bid) {
        String query = "SELECT s FROM Snippet s WHERE bid = "+bid; //select all snippets in a specific board
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            List<Snippet> listSnippets = manager.createQuery(query).getResultList();
            tx.commit();
            return listSnippets; //return empty list, list.size() == 0
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
    }
}
