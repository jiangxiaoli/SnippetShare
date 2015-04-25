package edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.sjsu.cmpe275.team6.SnippetShare.model.Comment;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Snippet;

public class SnippetAdapter implements JsonSerializer<Snippet> {

	@Override
	public JsonElement serialize(Snippet snippet, Type type, JsonSerializationContext jsonSerializationContext) {
		// TODO Auto-generated method stub
		JsonObject snippetObj = new JsonObject();
		
		snippetObj.addProperty("sid", snippet.getSid());
		snippetObj.addProperty("title", snippet.getTitle());
		snippetObj.addProperty("content", snippet.getContent());
		snippetObj.addProperty("language", snippet.getLanguage());
		snippetObj.addProperty("url", snippet.getUrl());
		snippetObj.addProperty("createdAt", snippet.getCreatedAt());
		snippetObj.addProperty("updateAt", snippet.getUpdatedAt());
		snippetObj.addProperty("numOfComments", snippet.getNumberOfComments());
		
		//map for author
		JsonObject authorObj = new JsonObject();
        authorObj.addProperty("userid", snippet.getAuthor().getUserid());
        authorObj.addProperty("username", snippet.getAuthor().getUsername());
        authorObj.addProperty("userAvatarId", snippet.getAuthor().getUserAvatarId());
        snippetObj.add("author", authorObj);
		
        //map for board
        JsonObject boardObj = new JsonObject();
        boardObj.addProperty("bid",snippet.getBoard().getBid());
        boardObj.addProperty("title",snippet.getBoard().getTitle());
        boardObj.addProperty("category",snippet.getBoard().getCategory());
        boardObj.addProperty("description",snippet.getBoard().getDescription());
        snippetObj.add("board",boardObj);
        
        //map for comments
        List<Comment> comments = snippet.getComments();
        if(comments!=null){
        	JsonArray commentsList = new JsonArray();
        	for(Comment c : comments){
        		JsonObject commentItemObj = new JsonObject();
        		commentItemObj.addProperty("cid", c.getCid());
        		commentItemObj.addProperty("content", c.getContent());
        		commentItemObj.addProperty("time", c.getTime().toString());
        		
        		JsonObject commentAuthorObj = new JsonObject();
        		commentAuthorObj.addProperty("userid", c.getUser().getUserid());
        		commentAuthorObj.addProperty("username", c.getUser().getUsername());
        		commentAuthorObj.addProperty("userAvatarId", c.getUser().getUserAvatarId());
        		commentItemObj.add("author", commentAuthorObj);
        		
        		commentsList.add(commentItemObj);
        	}
        	snippetObj.add("comments", commentsList);
        }
        
		return snippetObj;
	}

}
