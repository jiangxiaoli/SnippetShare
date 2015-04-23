package edu.sjsu.cmpe275.team6.SnippetShare.dao;

import edu.sjsu.cmpe275.team6.SnippetShare.model.Board;


import java.util.List;

/**
 * Created by Rucha on 4/15/15.
 */
public interface BoardDAO {

    public boolean insert(Board board);
    public Board findByBoardId(int bid);
    public void update(Board board);
    public boolean delete(int bid);
    public List<Board> allBoards(int ownerid);
}
