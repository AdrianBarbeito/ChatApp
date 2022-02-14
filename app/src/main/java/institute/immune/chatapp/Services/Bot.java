package institute.immune.chatapp.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class Bot extends Service {
    public Bot() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}