package edu.sjsu.cmpe275.team6.SnippetShare.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import edu.sjsu.cmpe275.team6.SnippetShare.model.Comment;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Snippet;

public class JpaCommentDAO implements CommentDAO {

	private EntityManagerFactory entityManagerFactory;
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }
	
	@Override
	public boolean insert(Comment comment) {
		EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        java.util.Date date= new java.util.Date();
        comment.setTime(date);
        try {
            tx.begin();
            manager.persist(comment);
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
	public Comment findByCommentId(int cid) {
		EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            Comment comment = manager.find(Comment.class, cid);
            tx.commit();
            return comment;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
	}

	@Override
	public boolean delete(int cid) {
		EntityManager manager = entityManagerFactory.createEntityManager();
        Comment comment = manager.find(Comment.class,cid);
        EntityTransaction tx = manager.getTransaction();
        
        try{
            tx.begin();
            if(comment!=null){
                System.out.println(comment.getCid()+", "+comment.getContent()+", "+ comment.getUser());
                //manager.refresh(snippet); //for cascade delete
                manager.remove(comment);
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
	public List<Comment> allComments(int sid) {
		String query = "SELECT c FROM Comment c WHERE sid = "+sid; //select all comments in a specific snippet
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            List<Comment> listComments = manager.createQuery(query).getResultList();
            tx.commit();
            return listComments; //return empty list, list.size() == 0
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
	}

}
