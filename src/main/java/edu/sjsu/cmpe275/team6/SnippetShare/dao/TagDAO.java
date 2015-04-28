package edu.sjsu.cmpe275.team6.SnippetShare.dao;

import java.util.List;

import edu.sjsu.cmpe275.team6.SnippetShare.model.Tag;

public interface TagDAO {
	public boolean insert(Tag tag);
    public Tag findByTagId(int tid);
    public boolean delete(int tid);
    public List<Tag> allTags(int tid);
}
