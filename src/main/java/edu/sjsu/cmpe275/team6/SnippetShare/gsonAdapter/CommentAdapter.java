package edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.sjsu.cmpe275.team6.SnippetShare.model.Comment;

public class CommentAdapter implements JsonSerializer<Comment> {

	@Override
	public JsonElement serialize(Comment comment, Type type, JsonSerializationContext jsonSerializationContext) {
		// TODO Auto-generated method stub
		JsonObject commentObj = new JsonObject();
		
		commentObj.addProperty("cid", comment.getCid());
		commentObj.addProperty("content", comment.getContent());
		commentObj.addProperty("createAt", comment.getTime().toString());
		
		//map author information
		if(comment.getUser()!=null){
			JsonObject userObj = new JsonObject();
	        userObj.addProperty("userid", comment.getUser().getUserid());
	        userObj.addProperty("username", comment.getUser().getUsername());
	        userObj.addProperty("userAvatarId", comment.getUser().getUserAvatarId());
	        commentObj.add("user", userObj);
		}
        
        //map snippet information
		if(comment.getSnippet()!=null){
	        JsonObject snippetObj = new JsonObject();
	        snippetObj.addProperty("sid", comment.getSnippet().getSid());
	        snippetObj.addProperty("title", comment.getSnippet().getTitle());
	        snippetObj.addProperty("content", comment.getSnippet().getContent());
	        snippetObj.addProperty("url", comment.getSnippet().getUrl());
	        snippetObj.addProperty("createdAt", comment.getSnippet().getCreatedAt());
	        snippetObj.addProperty("updatedAt", comment.getSnippet().getUpdatedAt());
	        if(comment.getSnippet().getAuthor()!=null){
		        JsonObject authorObj = new JsonObject();
		        authorObj.addProperty("userid", comment.getSnippet().getAuthor().getUserid());
		        authorObj.addProperty("username", comment.getSnippet().getAuthor().getUsername());
		        authorObj.addProperty("userAvatarId", comment.getSnippet().getAuthor().getUserAvatarId());
		        snippetObj.add("author", authorObj);
	        }     
	        commentObj.add("snippet", snippetObj);
		}
		
		return commentObj;
	}

}
