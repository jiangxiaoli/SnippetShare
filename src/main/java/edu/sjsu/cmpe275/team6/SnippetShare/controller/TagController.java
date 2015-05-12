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

import edu.sjsu.cmpe275.team6.SnippetShare.dao.TagDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.SnippetDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter.CommentAdapter;
import edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter.TagAdapter;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Comment;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Tag;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Snippet;


@Controller
@RequestMapping("/snippets/{sid}/tags")
public class TagController {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    SnippetDAO snippetDAO = (SnippetDAO) context.getBean("snippetDAO");
    TagDAO tagDAO = (TagDAO) context.getBean("tagDAO");
	
	//1. POST Create a tag
	@RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createTag(
            @PathVariable int sid,
            @RequestParam(value = "content", required = true) String content) {

        Tag tag = new Tag(content);

        Snippet s = snippetDAO.findBySnippetId(sid);
        tag.setSnippet(s);

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Tag.class, new TagAdapter()).create();

        try {
            tagDAO.insert(tag);
            System.out.println("Created a new tag::" + tag.getTid());
            Tag tag1 = tagDAO.findByTagId(tag.getTid());
            String result = gson.toJson(tag1);
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } catch (RuntimeException e){
            System.out.println("fail to create tag");
            String result = new Gson().toJson("Fail to create tag");
            return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
        }

    }
	
	
	//2. delete a tag
    @RequestMapping(value = "/{tid}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteComment(@PathVariable int sid, @PathVariable int tid) {
        Tag tag = tagDAO.findByTagId(tid);

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Tag.class, new TagAdapter()).create();

        if(tag != null){
            try {
                tagDAO.delete(tid);
                System.out.println("tag-"+tid+" deleted !!");
                String result = gson.toJson(tag);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            } catch (RuntimeException e){
                String result = new Gson().toJson("Fail to delete tag- "+tid+"!");
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }

        } else {
            String result = new Gson().toJson("tag-"+tid+" not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }
	
    
    
  //3. GET get all tags in a snippet
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> showComments(@PathVariable int sid) {
        List<Tag> tagList = tagDAO.allTags(sid);

        //gson to build and map user class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Tag.class, new TagAdapter()).create();

        if(tagList != null){

            for(Tag t: tagList) {
                System.out.println("Show tag details::" + t.getTid());
            }

            String result = gson.toJson(tagList);

            return new ResponseEntity<String>(result, HttpStatus.OK);
        }else{
            //no user
            String result = new Gson().toJson("No tag found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }
}
