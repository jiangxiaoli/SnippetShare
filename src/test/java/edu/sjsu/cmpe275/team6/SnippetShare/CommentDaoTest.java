package edu.sjsu.cmpe275.team6.SnippetShare;

import edu.sjsu.cmpe275.team6.SnippetShare.dao.CommentDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Comment;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rucha on 5/1/15.
 */
public class CommentDaoTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    CommentDAO commentDAO = (CommentDAO) context.getBean("commentDAO");

    @Test
    public void createComment(){
        Comment comment = new Comment("Very good code");
        boolean created = commentDAO.insert(comment);
        assert (created);
    }

    @Test
    public void getComment(){
        Comment comment = commentDAO.findByCommentId(3);
        assert (comment !=null);
    }

    @Test
    public void getAllComments(){
        List<Comment> comments = new ArrayList<Comment>();
        comments = commentDAO.allComments(3);
        assert (!comments.isEmpty());
    }

    @Test
    public void deleteComment(){
        Comment deleteComment = new Comment("deleteComment");
        commentDAO.insert(deleteComment);
        int id = deleteComment.getCid();
        commentDAO.delete(id);
        assert (commentDAO.findByCommentId(id)== null);
    }

}
