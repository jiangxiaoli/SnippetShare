package edu.sjsu.cmpe275.team6.SnippetShare.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.sjsu.cmpe275.team6.SnippetShare.dao.CommentDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.SnippetDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter.CommentAdapter;
import edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter.SnippetAdapter;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Comment;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Snippet;


@Controller
@RequestMapping("/snippets/{sid}/comments")
public class CommentController {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    SnippetDAO snippetDAO = (SnippetDAO) context.getBean("snippetDAO");
    CommentDAO commentDAO = (CommentDAO) context.getBean("commentDAO");
    
    //1. POST Create a comment
    
    //need the author info after auth module is done!! 
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createComment(
            @PathVariable int sid,
            @RequestParam(value = "content", required = true) String content) {

        Comment comment = new Comment(content);

        Snippet s = snippetDAO.findBySnippetId(sid);
        comment.setSnippet(s);

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Comment.class, new CommentAdapter()).create();

        try {
            commentDAO.insert(comment);
            System.out.println("Created a new comment::" + comment.getCid());
            Comment comment1 = commentDAO.findByCommentId(comment.getCid());
            String result = gson.toJson(comment1);
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } catch (RuntimeException e){
            System.out.println("fail to create comment");
            String result = new Gson().toJson("Fail to create comment");
            return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
        }

    }
    
    
  
    //2. delete a comment
    @RequestMapping(value = "/{cid}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteComment(@PathVariable int sid, @PathVariable int cid) {
        Comment comment = commentDAO.findByCommentId(cid);

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Comment.class, new CommentAdapter()).create();

        if(comment != null){
            try {
                commentDAO.delete(cid);
                System.out.println("comment-"+cid+" deleted !!");
                String result = gson.toJson(comment);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            } catch (RuntimeException e){
                String result = new Gson().toJson("Fail to delete comment- "+cid+"!");
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }

        } else {
            String result = new Gson().toJson("comment-"+cid+" not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }
    
    
    //3. GET get all comment in a snippet
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> showComments(@PathVariable int sid) {
        List<Comment> commentList = commentDAO.allComments(sid);

        //gson to build and map user class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Comment.class, new CommentAdapter()).create();

        if(commentList != null){

            for(Comment c: commentList) {
                System.out.println("Show comment details::" + c.getCid());
            }

            String result = gson.toJson(commentList);

            return new ResponseEntity<String>(result, HttpStatus.OK);
        }else{
            //no user
            String result = new Gson().toJson("No comment found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }
}
