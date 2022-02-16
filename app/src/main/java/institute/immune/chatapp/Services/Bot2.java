package institute.immune.chatapp.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import institute.immune.chatapp.Activities.SearchActivity;
import institute.immune.chatapp.Class.MyOpenHelper;
import institute.immune.chatapp.Class.User;

public class Bot2 extends Service {
    private MyOpenHelper db;
    User bot2;
    private static SearchActivity listener;
    public Bot2() {
        //Coger Usuario aleatorio de la BD
        // Asignarle la categoría Deportes
        //Mantenerlo en escucha
        //Crear un chat si el usuario ha buscado Deportes


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
        bot2 = db.getUserById(Math.round( Math.random() * 10));
        bot2.setCategory("Sport");
    }
    public static void setupdateListener(SearchActivity ma){
        listener = ma;
    }
}