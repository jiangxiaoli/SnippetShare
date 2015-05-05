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
                    JsonObject userItemObj = new JsonObject();
                    userItemObj.addProperty("bid",b.getBid());
                    userItemObj.addProperty("ownerId",user.getUserid());
                    userItemObj.addProperty("title",b.getTitle());
                    userItemObj.addProperty("category",b.getCategory());
                    userItemObj.addProperty("isPublic",b.getIsPublic());
                    userItemObj.addProperty("createdAt", b.getCreatedAt());
                    userItemObj.addProperty("updatedAt", b.getUpdatedAt());
                    userItemObj.addProperty("description",b.getDescription());
                    userItemObj.addProperty("numberOfSnippets",b.getNumberOfSnippets());

                    List<User> requestors = b.getRequestors();
                    JsonArray requestorIdArr = new JsonArray();
                    if (requestors != null) {
                        for (User u : requestors) {
                            requestorIdArr.add(new JsonPrimitive( u.getUserid()));
                        }
                    }
                    userItemObj.add("requestorIds", requestorIdArr);

                    List<User> members = b.getMembers();
                    JsonArray memberIdArr = new JsonArray();
                    if (members != null) {
                        for (User u : members) {
                            memberIdArr.add(new JsonPrimitive(u.getUserid()));
                        }
                    }
                    userItemObj.add("memberIds", memberIdArr);

                    boardListArr.add(userItemObj); //add each item to board array

                }
                userObj.add("boards", boardListArr); //add board array to user obj
            }

        return userObj;
    }
}
