package edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import edu.sjsu.cmpe275.team6.SnippetShare.model.Tag;

public class TagAdapter implements JsonSerializer<Tag>{

	@Override
	public JsonElement serialize(Tag tag, Type type, JsonSerializationContext jsonSerializationContext) {
		// TODO Auto-generated method stub
		JsonObject tagObj = new JsonObject();
		
		tagObj.addProperty("tid", tag.getTid());
		tagObj.addProperty("content", tag.getContent());
        
        //map snippet information
		if(tag.getSnippet()!=null){
	        JsonObject snippetObj = new JsonObject();
	        snippetObj.addProperty("sid", tag.getSnippet().getSid());
	        snippetObj.addProperty("title", tag.getSnippet().getTitle());
	        snippetObj.addProperty("content", tag.getSnippet().getContent());
	        snippetObj.addProperty("url", tag.getSnippet().getUrl());
	        snippetObj.addProperty("createdAt", tag.getSnippet().getCreatedAt());
	        snippetObj.addProperty("updatedAt", tag.getSnippet().getUpdatedAt());
	        if(tag.getSnippet().getAuthor()!=null){
		        JsonObject authorObj = new JsonObject();
		        authorObj.addProperty("userid", tag.getSnippet().getAuthor().getUserid());
		        authorObj.addProperty("username", tag.getSnippet().getAuthor().getUsername());
		        authorObj.addProperty("userAvatarId", tag.getSnippet().getAuthor().getUserAvatarId());
		        snippetObj.add("author", authorObj);
	        }     
	        tagObj.add("snippet", snippetObj);
		}
		
		return tagObj;
	}
	
}
