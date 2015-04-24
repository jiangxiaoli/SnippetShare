package edu.sjsu.cmpe275.team6.SnippetShare.dao;

import edu.sjsu.cmpe275.team6.SnippetShare.model.Snippet;

import java.util.List;

/**
 * Created by Rucha on 4/16/15.
 */
public interface SnippetDAO {


        public boolean insert(Snippet snippet);
        public Snippet findBySnippetId(int sid);
        public void update(Snippet snippet);
        public boolean delete(int sid);
        public List<Snippet> allSnippets(int bid);


}
