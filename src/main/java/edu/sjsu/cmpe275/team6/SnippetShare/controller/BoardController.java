package edu.sjsu.cmpe275.team6.SnippetShare.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.BoardDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter.BoardAdapter;
import edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter.UserAdapter;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Board;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users/{userid}/boards")
public class BoardController {

    ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    BoardDAO boardDAO =(BoardDAO) context.getBean("boardDAO");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");

    //1. POST Create a board
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createUser(
            @PathVariable int userid,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "category", required = true) String category,
            @RequestParam(value = "isPublic", required = true) Boolean isPublic,
            @RequestParam(value = "description", required = false) String description) {

        Board board = new Board(title, category, isPublic);
        board.setDescription(description);

        User owener = userDAO.findByUserId(userid);
        board.setOwner(owener);

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Board.class, new BoardAdapter()).create();

        try {
            boardDAO.insert(board);
            System.out.println("Created a new board::" + board.getBid());
            Board board1 = boardDAO.findByBoardId(board.getBid());
            String result = gson.toJson(board1);
            return new ResponseEntity<String>(result, HttpStatus.OK);
        } catch (RuntimeException e){
            System.out.println("fail to create board");
            String result = new Gson().toJson("Fail to create board");
            return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
        }

    }

    //2. GET get a board
    @RequestMapping(value = "/{bid}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> showUser(@PathVariable int userid,
                                           @PathVariable int bid) {
        try {
            Board board = boardDAO.findByBoardId(bid);
            //gson to build and map user class
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.registerTypeAdapter(Board.class, new BoardAdapter()).create();

            if (board != null) {
                System.out.println("Show board details::" + board.getBid());
                String result = gson.toJson(board);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            } else {
                //user not found
                String result = new Gson().toJson("board-" + bid + " not found");
                return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e){
            System.out.println("fail to get board");
            String result = new Gson().toJson("Fail to get board");
            return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
        }

    }

    //3.Update the User
//    @RequestMapping(value = "/{userid}", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<String> updateUser(
//            @RequestParam(value = "username", required = false) String username,
//            @RequestParam(value = "pwd", required = false) String pwd,
//            @RequestParam(value = "email", required = false) String email,
//            @RequestParam(value = "userAvatarId", required = false) String userAvatarId,
//            @RequestParam(value = "aboutMe", required = false) String aboutMe,
//            @PathVariable int userid) {
//
//        User user = userDAO.findByUserId(userid);
//        if(user != null) {
//            user.setUsername(username);
//            user.setPwd(pwd);
//            user.setEmail(email);
//            user.setUserAvatarId(userAvatarId);
//            user.setAboutMe(aboutMe);
//
//            System.out.println("User-" + user.getID());
//
//            //gson to build and map user class
//            GsonBuilder gsonBuilder = new GsonBuilder();
//            Gson gson = gsonBuilder.registerTypeAdapter(User.class, new UserAdapter()).create();
//
//            try {
//                userDAO.update(user);
//                System.out.println("User-"+userid+" Updated!!");
//                user = userDAO.findByUserId(userid);
//                String result = gson.toJson(user);
//                return new ResponseEntity<String>(result, HttpStatus.OK);
//            } catch (Exception e){
//                String result = new Gson().toJson("Fail to update User-" + userid);
//                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
//            }
//
//        } else {
//            String result = new Gson().toJson("User-" + userid + " not found");
//            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
//        }
//
//    }
//
//    //4. GET get all board
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> showBoards(@PathVariable int userid) {
        List<Board> boardList = boardDAO.allBoards(userid);

        //gson to build and map user class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Board.class, new BoardAdapter()).create();

        if(boardList != null){

            for(Board board: boardList) {
                System.out.println("Show board details::" + board.getBid());

            }

            String result = gson.toJson(boardList);

            return new ResponseEntity<String>(result, HttpStatus.OK);
        }else{
            //no user
            String result = new Gson().toJson("No board found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }



}
