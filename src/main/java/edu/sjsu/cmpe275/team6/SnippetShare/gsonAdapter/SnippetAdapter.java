package edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.sjsu.cmpe275.team6.SnippetShare.model.*;

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
		if(snippet.getAuthor()!=null){
			JsonObject authorObj = new JsonObject();
	        authorObj.addProperty("userid", snippet.getAuthor().getUserid());
	        authorObj.addProperty("username", snippet.getAuthor().getUsername());
	        authorObj.addProperty("userAvatarId", snippet.getAuthor().getUserAvatarId());
	        snippetObj.add("author", authorObj);
		}
		
        //map for board
		if(snippet.getBoard()!=null){
			Board board = snippet.getBoard();
        	JsonObject boardObj = new JsonObject();
	        boardObj.addProperty("bid", board.getBid());
			boardObj.addProperty("ownerId",board.getOwner().getUserid());
			boardObj.addProperty("title",board.getTitle());
	        boardObj.addProperty("category",board.getCategory());
	        boardObj.addProperty("description",board.getDescription());

			//mapping members list
			List<User> memebers = board.getMembers();
			if(memebers != null) {
				JsonArray membersList = new JsonArray();
				for (User user : memebers) {
					JsonObject userItemObj = new JsonObject();
					userItemObj.addProperty("userid", user.getUserid());
					userItemObj.addProperty("username", user.getUsername());
					userItemObj.addProperty("userAvatarId", user.getUserAvatarId());
					membersList.add(userItemObj); //add each user to memeber array
				}
				boardObj.add("members", membersList); //add members array to board obj
			}

			//mapping requestors list
			List<User> requestors = board.getRequestors();
			if(requestors != null) {
				JsonArray requestorsList = new JsonArray();
				for (User user : requestors) {
					JsonObject userItemObj = new JsonObject();
					userItemObj.addProperty("userid", user.getUserid());
					userItemObj.addProperty("username", user.getUsername());
					userItemObj.addProperty("userAvatarId", user.getUserAvatarId());
					requestorsList.add(userItemObj); //add each user to requestors array
				}
				boardObj.add("requestors", requestorsList); //add requestors array to board obj
			}
	        snippetObj.add("board",boardObj);
		}
        
        //map for comments
        List<Comment> comments = snippet.getComments();
        if(comments!=null){
        	JsonArray commentsList = new JsonArray();
        	for(Comment c : comments){
        		JsonObject commentItemObj = new JsonObject();
				commentItemObj.addProperty("cid", c.getCid());
				commentItemObj.addProperty("content", c.getContent());
				commentItemObj.addProperty("createAt", c.getTime().toString());

				//map author information
				if(c.getUser()!=null){
					JsonObject userObj = new JsonObject();
					userObj.addProperty("userid", c.getUser().getUserid());
					userObj.addProperty("username", c.getUser().getUsername());
					userObj.addProperty("userAvatarId", c.getUser().getUserAvatarId());
					commentItemObj.add("user", userObj);
				}
        		
        		JsonObject commentAuthorObj = new JsonObject();
        		commentAuthorObj.addProperty("userid", c.getUser().getUserid());
        		commentAuthorObj.addProperty("username", c.getUser().getUsername());
        		commentAuthorObj.addProperty("userAvatarId", c.getUser().getUserAvatarId());
        		commentItemObj.add("author", commentAuthorObj);
        		
        		commentsList.add(commentItemObj);
        	}
        	snippetObj.add("comments", commentsList);
        }
        
        
        //map for tags
        List<Tag> tags = snippet.getTags();
        if(tags!=null){
        	JsonArray tagsList = new JsonArray();
        	for(Tag t : tags){
        		JsonObject tagItemObj = new JsonObject();
        		tagItemObj.addProperty("tid", t.getTid());
        		tagItemObj.addProperty("content", t.getContent());
        		tagsList.add(tagItemObj);
        	}
        	snippetObj.add("tags", tagsList);
        }
        
		return snippetObj;
	}

}
