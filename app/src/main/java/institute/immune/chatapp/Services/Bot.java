package institute.immune.chatapp.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.ArrayList;

import institute.immune.chatapp.Activities.SearchActivity;
import institute.immune.chatapp.Class.MyOpenHelper;
import institute.immune.chatapp.Class.User;

public class Bot extends Service {
    private MyOpenHelper db;
    User bot;
    private static SearchActivity listener;
    public Bot() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
        db = new MyOpenHelper(this);
        randomUser();
    }
    public User randomUser(){
        ArrayList<User> userList =  db.searchByCategory("Sport");
        return userList.get((int)Math.round( Math.random() * userList.size()));
    }

    public static void setupdateListener(SearchActivity ma){
        listener = ma;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
////
        ////
////
        ////

