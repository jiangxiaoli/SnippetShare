package edu.sjsu.cmpe275.team6.SnippetShare;

import edu.sjsu.cmpe275.team6.SnippetShare.dao.SnippetDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.dao.TagDAO;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Snippet;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Tag;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rucha on 5/1/15.
 */
public class TagDaoTest {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
    TagDAO tagDAO = (TagDAO) context.getBean("tagDAO");
    SnippetDAO snippetDAO = (SnippetDAO) context.getBean("snippetDAO");

    @Test
    public void createTag(){
        Tag tag = new Tag("Java");
        Snippet snippet = snippetDAO.findBySnippetId(3);
        tag.setSnippet(snippet);
        boolean created = tagDAO.insert(tag);
        assert (created);
    }

    @Test
    public void getTag(){
        Tag tag = tagDAO.findByTagId(6);
        assert (tag!=null);
        assert (tag.getContent().equals("Java"));
    }

    @Test
    public void getAllTags(){
        List<Tag> tags = new ArrayList<Tag>();
        tags = tagDAO.allTags(1);
        assert (tags.size()==2);
    }

    @Test
    public void deleteTag(){
        Tag deleteTag = new Tag("deleteTag");
        tagDAO.insert(deleteTag);
        int id = deleteTag.getTid();

        boolean deleted =  tagDAO.delete(id);

        assert (deleted);
        assert (tagDAO.findByTagId(id)==null);
    }

}
