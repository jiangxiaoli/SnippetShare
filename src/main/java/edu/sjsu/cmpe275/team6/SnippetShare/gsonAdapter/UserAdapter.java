package edu.sjsu.cmpe275.team6.SnippetShare.gsonAdapter;

import com.google.gson.*;
import edu.sjsu.cmpe275.team6.SnippetShare.model.Board;
import edu.sjsu.cmpe275.team6.SnippetShare.model.User;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Corn on 4/6/15.
 * @author Rucha
 */
public class UserAdapter implements JsonSerializer<User> {

    @Override
    public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject userObj = new JsonObject();
        userObj.addProperty("userid",user.getUserid());
        userObj.addProperty("username",user.getUsername());
        userObj.addProperty("email",user.getEmail());
        userObj.addProperty("pwd",user.getPwd());
        userObj.addProperty("userAvatarId",user.getUserAvatarId());
        userObj.addProperty("aboutMe",user.getAboutMe());


        //mapping board
        List<Board> boards = user.getBoards();
            if (boards != null) {
                JsonArray boardListArr = new JsonArray();
                for (Board b : boards) {
                    JsonObject boardObj = new JsonObject();
                    boardObj.addProperty("bid",b.getBid());
                    boardObj.addProperty("ownerId",user.getUserid());
                    boardObj.addProperty("title",b.getTitle());
                    boardObj.addProperty("category",b.getCategory());
                    boardObj.addProperty("isPublic",b.getIsPublic());
                    boardObj.addProperty("createdAt", b.getCreatedAt());
                    boardObj.addProperty("updatedAt", b.getUpdatedAt());
                    boardObj.addProperty("description",b.getDescription());
                    boardObj.addProperty("numberOfSnippets",b.getNumberOfSnippets());

                    List<User> memebers = b.getMembers();
                    if(memebers != null) {
                        JsonArray membersList = new JsonArray();
                        for (User u : memebers) {
                            JsonObject memberObj = new JsonObject();
                            memberObj.addProperty("userid", u.getUserid());
                            memberObj.addProperty("username", u.getUsername());
                            memberObj.addProperty("userAvatarId", u.getUserAvatarId());
                            membersList.add(memberObj); //add each user to memeber array
                        }
                        boardObj.add("members", membersList); //add members array to board obj
                    }

                    //mapping requestors list
                    List<User> requestors = b.getRequestors();
                    if(requestors != null) {
                        JsonArray requestorsList = new JsonArray();
                        for (User u : requestors) {
                            JsonObject requestorObj = new JsonObject();
                            requestorObj.addProperty("userid", u.getUserid());
                            requestorObj.addProperty("username", u.getUsername());
                            requestorObj.addProperty("userAvatarId", u.getUserAvatarId());
                            requestorsList.add(requestorObj); //add each user to requestors array
                        }
                        boardObj.add("requestors", requestorsList); //add requestors array to board obj
                    }

                    boardListArr.add(boardObj); //add each item to board array

                }
                userObj.add("boards", boardListArr); //add board array to user obj
            }

        return userObj;
    }
}
