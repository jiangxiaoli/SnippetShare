package edu.sjsu.cmpe275.team6.SnippetShare.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.BoardDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.UserDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter.BoardAdapter;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Board;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users/{userid}/boards")
public class BoardController {

    ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    BoardDAO boardDAO =(BoardDAO) context.getBean("boardDAO");
    UserDAO userDAO = (UserDAO) context.getBean("userDAO");

//    BoardDAO boardDAO;
//    UserDAO userDAO;
//
//    @Autowired
//    public BoardController(UserDAO userDAO,BoardDAO boardDAO){
//        this.userDAO = userDAO;
//        this.boardDAO = boardDAO;
//    }

    //1. POST Create a board
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> createBoard(
            @PathVariable int userid,
            @RequestParam(value = "title", required = true) String title,
            @RequestParam(value = "category", required = true) String category,
            @RequestParam(value = "isPublic", required = true) boolean isPublic,
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
    public ResponseEntity<String> showBoard(@PathVariable int userid,
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

    //3.Update the board
    //PAY ATTENTION to switch between isPublic (false-true)
    @RequestMapping(value = "/{bid}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> updateBoard(
            @PathVariable int userid,
            @PathVariable int bid,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "category", required = false) String category,
            @RequestParam(value = "isPublic", required = false) boolean isPublic,
            @RequestParam(value = "description", required = false) String description) {

        Board board = boardDAO.findByBoardId(bid);


        System.out.println("new title:" + title);

        System.out.println("new desc:" + description);


        if(board != null) {
            board.setTitle(title);
            board.setCategory(category);

            if(isPublic){
                //check if false-true, clean members and requestors
                if(board.getIsPublic() == false && isPublic == true)
                {
                    board.setMembers(new ArrayList<User>());
                    board.setRequestors(new ArrayList<User>());
                }
                board.setIsPublic(isPublic);
            }

            board.setDescription(description);

            //gson to build and map board class
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.registerTypeAdapter(Board.class, new BoardAdapter()).create();

            try {
                boardDAO.update(board);
                System.out.println("Board-" + bid + " Updated!!");
                board = boardDAO.findByBoardId(bid);

                System.out.println("new board:" + board.getTitle() +", " + board.getDescription());

                String result = gson.toJson(board);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            } catch (Exception e){
                String result = new Gson().toJson("Fail to update Board-" + bid);
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }

        } else {
            String result = new Gson().toJson("Board-" + bid + " not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }

    }

    //4. GET get all board
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

    //5. delete a board
    @RequestMapping(value = "/{bid}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteBoard(@PathVariable int bid) {
        Board board = boardDAO.findByBoardId(bid);

        //gson to build and map player class
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.registerTypeAdapter(Board.class, new BoardAdapter()).create();

        if(board != null){
            try {
                boardDAO.delete(bid);
                System.out.println("board-"+bid+" deleted !!");
                String result = gson.toJson(board);
                return new ResponseEntity<String>(result, HttpStatus.OK);
            } catch (RuntimeException e){
                String result = new Gson().toJson("Fail to delete board- "+bid+"!");
                return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
            }

        } else {
            String result = new Gson().toJson("Board-"+bid+" not found");
            return new ResponseEntity<String>(result, HttpStatus.NOT_FOUND);
        }
    }


}
