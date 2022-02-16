package institute.immune.chatapp.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import institute.immune.chatapp.Activities.SearchActivity;
import institute.immune.chatapp.Class.MyOpenHelper;
import institute.immune.chatapp.Class.User;

public class Bot3 extends Service {
    private MyOpenHelper db;
    User bot3;
    private static SearchActivity listener;
    public Bot3() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        db = new MyOpenHelper(this);
        RandomUser();
    }
    public void RandomUser(){
        //EL número se sustituira por el tamaño de la BBDD -1
        //bot3 = db.getUserById(Math.round( Math.random() * 10));
        //bot3.setCategory("Sport");
    }
    public static void setupdateListener(SearchActivity ma){
        listener = ma;
    }
}