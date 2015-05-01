package edu.sjsu.cmpe275.team6.SnippetShare;

import edu.sjsu.cmpe275.team6.SnippetShare.dao.SnippetDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Snippet;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rucha on 5/1/15.
 */
public class SnippetDaoTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    SnippetDAO snippetDAO = (SnippetDAO) context.getBean("snippetDAO");

   @Test
    public void createSnippet(){
       Snippet snippet = new Snippet("testSnippet","public static void main (String[] args)");
       boolean created = snippetDAO.insert(snippet);
       assert (created);
   }

    @Test
    public void updateSnippet(){
        Snippet snippet = snippetDAO.findBySnippetId(3);
        snippet.setLanguage("Java");
        snippetDAO.update(snippet);
        assert (snippetDAO.findBySnippetId(3).getLanguage().equals("Java"));
    }

    @Test
    public void getSnippet(){
        Snippet snippet = snippetDAO.findBySnippetId(3);
        assert (snippet!=null);
    }

    @Test
    public void getAllSnippets(){
        List<Snippet> snippets = new ArrayList<Snippet>();
        snippets = snippetDAO.allSnippets(1);
        assert(snippets.size()==2);
    }

    public void deleteSnippet(){
        Snippet testDelete =  new Snippet("testDelete","public void delete()");
        snippetDAO.insert(testDelete);
        int id = testDelete.getSid();
        boolean deleted = snippetDAO.delete(id);
        assert (deleted);
        assert (snippetDAO.findBySnippetId(id)==null);
    }

}
