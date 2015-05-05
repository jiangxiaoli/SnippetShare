package edu.sjsu.cmpe275.team6.SnippetShare.controller;

import com.google.gson.Gson;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.BoardDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.RequestDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Board;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
import org.omg.CORBA.Request;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Corn on 4/22/15.
 */

@Controller
@RequestMapping("/request/{bid}/{userid}")
public class RequestController {

    ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    BoardDAO boardDAO =(BoardDAO) context.getBean("boardDAO");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");
    RequestDAO requestDAO = (RequestDAO) context.getBean("requestDAO");

    //1. add an request
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addRequest(@PathVariable int bid, @PathVariable int userid) {

        Board board = boardDAO.findByBoardId(bid);
        User user = userDAO.findByUserId(userid);

        if(board == null || user == null){
            String result = new Gson().toJson("Board or User not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        } else {
            try {
                requestDAO.addRequest(board, user);
                String result = new Gson().toJson("Create request: board-" + bid + " with user-" + userid + " succeed!!");
                return new ResponseEntity<String>(result, HttpStatus.OK);

            } catch (RuntimeException e) {
                String result = new Gson().toJson("Fail to send request");
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }
        }
    }

    //2. arrpove an request
    @RequestMapping(value = "/approve", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> approveRequest(@PathVariable int bid, @PathVariable int userid) {
        System.out.println("get approve request. bid/userid:"+bid+"/"+userid);
        Board board = boardDAO.findByBoardId(bid);
        User user = userDAO.findByUserId(userid);

        if(board == null || user == null){
            String result = new Gson().toJson("Board or User not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        } else {
            try {
                requestDAO.approveRequest(board, user);
                String result = new Gson().toJson("Request approved: board-" + bid + " with user-" + userid + " succeed!!");
                return new ResponseEntity<String>(result, HttpStatus.OK);

            } catch (RuntimeException e) {
                String result = new Gson().toJson("Fail to send request");
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }
        }
    }

    //3. deny an request
    @RequestMapping(value = "/deny", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> denyRequest(@PathVariable int bid, @PathVariable int userid) {

        Board board = boardDAO.findByBoardId(bid);
        User user = userDAO.findByUserId(userid);

        if(board == null || user == null){
            String result = new Gson().toJson("Board or User not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        } else {
            try {
                requestDAO.denyRequest(board, user);
                String result = new Gson().toJson("Request denied: board-" + bid + " with user-" + userid + " succeed!!");
                return new ResponseEntity<String>(result, HttpStatus.OK);

            } catch (RuntimeException e) {
                String result = new Gson().toJson("Fail to send request");
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }
        }
    }

    //4. remove an access
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> removeAccess(@PathVariable int bid, @PathVariable int userid) {

        Board board = boardDAO.findByBoardId(bid);
        User user = userDAO.findByUserId(userid);

        if(board == null || user == null){
            String result = new Gson().toJson("Board or User not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        } else {
            try {
                requestDAO.removeAccess(board, user);
                String result = new Gson().toJson("Remove Access: board-" + bid + " with user-" + userid + " succeed!!");
                return new ResponseEntity<String>(result, HttpStatus.OK);

            } catch (RuntimeException e) {
                String result = new Gson().toJson("Fail to send request");
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }
        }
    }



}
