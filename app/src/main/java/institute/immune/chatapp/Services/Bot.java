package institute.immune.chatapp.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;

import institute.immune.chatapp.Activities.ChatActivity;
import institute.immune.chatapp.Activities.ConversationsActivity;
import institute.immune.chatapp.Activities.ProfileActivity;
import institute.immune.chatapp.Activities.SearchActivity;
import institute.immune.chatapp.Class.MyOpenHelper;
import institute.immune.chatapp.Class.User;

public class Bot extends Service {
    private MyOpenHelper db;
    User bot;
    private static ChatActivity listenerChat;
    private static ConversationsActivity listenerConversations;
    @Override
    public void onCreate() {
        db = new MyOpenHelper(this);
        super.onCreate();
        bot = randomUser();
    }
    public User randomUser(){
        ArrayList<User> userList =  db.searchByCategory("politic");
        System.out.println(userList);
        return userList.get((int)Math.round( Math.random() * (userList.size()-1)));
    }

    public static void setupdateListener(ChatActivity chatActivity){
        listenerChat = chatActivity;
    }

    public static void setupdateListener(ConversationsActivity conversationsActivity){
        listenerConversations = conversationsActivity;
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

