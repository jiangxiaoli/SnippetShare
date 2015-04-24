package edu.sjsu.cmpe275.team6.SnippetShare.controller;

import java.util.ArrayList;
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

import edu.sjsu.cmpe275.team6.SnippetShare.dao.BoardDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.SnippetDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter.BoardAdapter;
import edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter.SnippetAdapter;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Board;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Snippet;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;



@Controller
@RequestMapping("/boards/{bid}/snippets")
public class SnippetController {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    BoardDAO boardDAO =(BoardDAO) context.getBean("boardDAO");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    SnippetDAO snippetDAO = (SnippetDAO) context.getBean("snippetDAO");
    
    //1. POST Create a snippet
    
    //Author need to be added after auth module done and can be send to controller!!
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createSnippet(
            @PathVariable int bid,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "language", required = false) String language,
            @RequestParam(value = "url", required = false) String url) {

        Snippet snippet = new Snippet(title, content);
        snippet.setLanguage(language);
        snippet.setUrl(url);

        Board b = boardDAO.findByBoardId(bid);
        snippet.setBoard(b);

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Snippet.class, new SnippetAdapter()).create();

        try {
            snippetDAO.insert(snippet);
            System.out.println("Created a new snippet::" + snippet.getSid());
            Snippet snippet1 = snippetDAO.findBySnippetId(snippet.getSid());
            String result = gson.toJson(snippet1);
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } catch (RuntimeException e){
            System.out.println("fail to create snippet");
            String result = new Gson().toJson("Fail to create snippet");
            return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
        }

    }
	
    
    
    //2. GET get a board
    @RequestMapping(value = "/{sid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> showSnippet(@PathVariable int bid,
                                           @PathVariable int sid) {
        try {
            Snippet snippet = snippetDAO.findBySnippetId(sid);
            //gson to build and map user class
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.registerTypeAdapter(Snippet.class, new SnippetAdapter()).create();

            if (snippet != null) {
                System.out.println("Show snippet details::" + snippet.getSid());
                String result = gson.toJson(snippet);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            } else {
                //user not found
                String result = new Gson().toJson("snippet-" + sid + " not found");
                return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e){
            System.out.println("fail to get snippet");
            String result = new Gson().toJson("Fail to get snippet");
            return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
        }

    }
    
    
    //3.Update the snippet
    @RequestMapping(value = "/{sid}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> updateSnippet(
    		@PathVariable int bid,
            @PathVariable int sid,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "content", required = true) String content,
            @RequestParam(value = "language", required = false) String language,
            @RequestParam(value = "url", required = false) String url) {

        Snippet snippet = snippetDAO.findBySnippetId(sid);

        if(snippet != null) {
            snippet.setTitle(title);
            snippet.setContent(content);
            snippet.setLanguage(language);
            snippet.setUrl(url);

            //gson to build and map board class
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.registerTypeAdapter(Snippet.class, new SnippetAdapter()).create();

            try {
                snippetDAO.update(snippet);
                System.out.println("Snippet-"+sid+" Updated!!");
                snippet = snippetDAO.findBySnippetId(sid);
                String result = gson.toJson(snippet);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            } catch (Exception e){
                String result = new Gson().toJson("Fail to update Snippet-" + sid);
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }

        } else {
            String result = new Gson().toJson("Snippet-" + sid + " not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }

    }
    
    
    
    //4. GET get all snippet in a board
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> showBoards(@PathVariable int bid) {
        List<Snippet> snippetList = snippetDAO.allSnippets(bid);

        //gson to build and map user class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Snippet.class, new SnippetAdapter()).create();

        if(snippetList != null){

            for(Snippet s: snippetList) {
                System.out.println("Show snippet details::" + s.getSid());
            }

            String result = gson.toJson(snippetList);

            return new ResponseEntity<String>(result, HttpStatus.OK);
        }else{
            //no user
            String result = new Gson().toJson("No board found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }
    
    
    //5. delete a snippet
    @RequestMapping(value = "/{sid}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteBoard(@PathVariable int bid, @PathVariable int sid) {
        Snippet snippet = snippetDAO.findBySnippetId(sid);

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Snippet.class, new SnippetAdapter()).create();

        if(snippet != null){
            try {
                snippetDAO.delete(sid);
                System.out.println("soard-"+sid+" deleted !!");
                String result = gson.toJson(snippet);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            } catch (RuntimeException e){
                String result = new Gson().toJson("Fail to delete snippet- "+sid+"!");
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }

        } else {
            String result = new Gson().toJson("Snippet-"+sid+" not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }
}
