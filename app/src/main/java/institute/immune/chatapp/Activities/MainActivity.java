package institute.immune.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import institute.immune.chatapp.Class.MyOpenHelper;
import institute.immune.chatapp.R;

public class MainActivity extends AppCompatActivity {
    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Conexión con API
     */
    private class HTTPReqTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            HttpURLConnection urlConnection = null;

            try {
                URL url = new URL("https://reqres.in/api/users?page=2");
                urlConnection = (HttpURLConnection) url.openConnection();

                int code = urlConnection.getResponseCode();
                if (code != 200) {
                    throw new IOException("Invalid response from server: " + code);
                }

                BufferedReader rd = new BufferedReader(new InputStreamReader(
                        urlConnection.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    procesarJson(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }

            return null;
        }
    }

    private void procesarJson(String line) throws JSONException {
        JSONObject jsonObject = new JSONObject(line);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        int var = jsonArray.length() - 1;
        while (var >= 0){
            JSONObject usuario = jsonArray.getJSONObject(var);
            String name = usuario.getString("first_name") + " " + usuario.getString("last_name");
            String mail = usuario.getString("email");
            db.crearUsuario(name, mail);
            var--;
        }
    }
}