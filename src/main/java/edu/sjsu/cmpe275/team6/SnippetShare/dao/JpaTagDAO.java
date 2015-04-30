package edu.sjsu.cmpe275.team6.SnippetShare.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import edu.sjsu.cmpe275.team6.SnippetShare.model.Comment;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Tag;

public class JpaTagDAO implements TagDAO{

	private EntityManagerFactory entityManagerFactory;
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory){
        this.entityManagerFactory = entityManagerFactory;
    }
	
	@Override
	public boolean insert(Tag tag) {
		EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();
            manager.persist(tag);
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
	public Tag findByTagId(int tid) {
		EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            Tag tag = manager.find(Tag.class, tid);
            tx.commit();
            return tag;
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
	}

	@Override
	public boolean delete(int tid) {
		EntityManager manager = entityManagerFactory.createEntityManager();
        Tag tag = manager.find(Tag.class,tid);
        EntityTransaction tx = manager.getTransaction();
        
        try{
            tx.begin();
            if(tag!=null){
                System.out.println(tag.getTid()+", "+tag.getContent());
                //manager.refresh(snippet); //for cascade delete
                manager.remove(tag);
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
	public List<Tag> allTags(int sid) {
		String query = "SELECT t FROM Tag t WHERE sid = "+sid; //select all comments in a specific snippet
        EntityManager manager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = manager.getTransaction();
        try {
            tx.begin();
            List<Tag> listTags = manager.createQuery(query).getResultList();
            tx.commit();
            return listTags; //return empty list, list.size() == 0
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        } finally {
            manager.close();
        }
	}

}
