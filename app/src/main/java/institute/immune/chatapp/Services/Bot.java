package institute.immune.chatapp.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

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
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        db = new MyOpenHelper(this);
        RandomizeDB();
    }
    public void RandomizeDB(){
        //EL número se sustituira por el tamaño de la BBDD -1
        //bot = db.getUserById(Math.round( Math.random() * 10)); //cambiar a category
        bot.setCategory("Sport");
        //bot = db.searchByCategory("Sport"); //pasarlo a array
    }
    public void SelectBot(){

    }
    public static void setupdateListener(SearchActivity ma){
        listener = ma;
    }
}
////
        ////
////
        ////

//Decier a mario que haga el xml del perfil