package edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter;

import com.google.gson.*;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Board;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Snippet;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;

import java.lang.reflect.Type;
import java.util.List;

public class BoardAdapter implements JsonSerializer<Board> {

    @Override
    public JsonElement serialize(Board board, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject boardObj = new JsonObject();
        boardObj.addProperty("bid",board.getBid());
        boardObj.addProperty("ownerId",board.getOwner().getUserid());
        boardObj.addProperty("title",board.getTitle());
        boardObj.addProperty("category",board.getCategory());
        boardObj.addProperty("isPublic",board.getIsPublic());
        boardObj.addProperty("createdAt", board.getCreatedAt());
        boardObj.addProperty("updateAt",board.getUpdatedAt());
        boardObj.addProperty("description",board.getDescription());
        boardObj.addProperty("numOfSnippets",board.getNumberOfSnippets());

        //mapping owner property
        JsonObject ownerObj = new JsonObject();
        ownerObj.addProperty("userid", board.getOwner().getUserid());
        ownerObj.addProperty("username", board.getOwner().getUsername());
        ownerObj.addProperty("userAvatarId", board.getOwner().getUserAvatarId());
        boardObj.add("owner", ownerObj);

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

        //mapping snippets list
        List<Snippet> snippets = board.getSnippets();
        if(snippets != null) {
            JsonArray snippetsList = new JsonArray();
            for (Snippet snippet : snippets) {
                JsonObject snippetObj = new JsonObject();
                snippetObj.addProperty("sid", snippet.getSid());
                snippetObj.addProperty("title", snippet.getTitle());
                snippetObj.addProperty("content", snippet.getContent());
                snippetObj.addProperty("url", snippet.getUrl());
                snippetObj.addProperty("language", snippet.getLanguage());
                snippetObj.addProperty("createdAt", snippet.getCreatedAt());
                snippetObj.addProperty("updateAt", snippet.getUpdatedAt());
                snippetObj.addProperty("numOfComments", snippet.getNumberOfComments());

                //mapping author property
                JsonObject authorObj = new JsonObject();
                authorObj.addProperty("userid", snippet.getAuthor().getUserid());
                authorObj.addProperty("username", snippet.getAuthor().getUsername());
                authorObj.addProperty("userAvatarId", snippet.getAuthor().getUserAvatarId());
                snippetObj.add("author", authorObj);

                snippetsList.add(snippetObj); //add each snippet to snippets array
            }
            boardObj.add("snippets", snippetsList); //add snippets array to board obj
        }

        return boardObj;
    }
}

