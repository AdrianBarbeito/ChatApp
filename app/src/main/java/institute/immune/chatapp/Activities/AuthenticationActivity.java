package institute.immune.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class AuthenticationActivity extends AppCompatActivity {
    private MyOpenHelper db;
    private EditText nickNameInput, mailInput, passwordinput;
    private Button credentialsBt, switchToBt;
    private Button consultaBt, apiBt, profileBt, searchBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);


        db = new MyOpenHelper(this);

        bindings();
        db.RandomDb();
        setListeners();
    }

    private void bindings() {
        nickNameInput = findViewById(R.id.nickNameInput);
        mailInput = findViewById(R.id.mailInput);
        passwordinput = findViewById(R.id.passwordInput);

        credentialsBt = findViewById(R.id.credentialsBt);
        switchToBt = findViewById(R.id.switchToBt);

        consultaBt = findViewById(R.id.consultaBt);
        apiBt = findViewById(R.id.apiBt);
        profileBt = findViewById(R.id.profileBt);
        searchBt = findViewById(R.id.SearchBt);
    }

    private void setListeners() {
        credentialsBt.setOnClickListener(credentialsListener);
        switchToBt.setOnClickListener(switchToListener);

        consultaBt.setOnClickListener(consultaListener);
        profileBt.setOnClickListener(consultaListener);
        searchBt.setOnClickListener(consultaListener);
        apiBt.setOnClickListener(apiListener);

    }

    public View.OnClickListener credentialsListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    public View.OnClickListener switchToListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (switchToBt.getText().toString().equalsIgnoreCase("sign in")){
                nickNameInput.setVisibility(View.VISIBLE);
                credentialsBt.setText(R.string.signIn);
                switchToBt.setText(R.string.login);
            } else if (switchToBt.getText().toString().equalsIgnoreCase("login")){
                nickNameInput.setVisibility(View.INVISIBLE);
                nickNameInput.layout(0, 50, 0, 0);
                credentialsBt.setText(R.string.login);
                switchToBt.setText(R.string.signIn);
            }
        }
    };


    public View.OnClickListener consultaListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()){

                case R.id.SearchBt:
                    intent = new Intent(view.getContext(), SearchActivity.class);
                    break;

                case R.id.profileBt:
                    intent = new Intent(view.getContext(), ProfileActivity.class);
                    break;

                case R.id.consultaBt:
                    intent = new Intent(view.getContext(), ConsultaActivity.class);
                    break;
            }
            startActivity(intent);
        }
    };

    public View.OnClickListener apiListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            new HTTPReqTask().execute();
        }
    };


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

        private void procesarJson(String line) throws JSONException {
            JSONObject jsonObject = new JSONObject(line);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            int var = jsonArray.length() - 1;
            while (var >= 0) {
                JSONObject usuario = jsonArray.getJSONObject(var);
                String name = usuario.getString("first_name") + usuario.getString("last_name");
                String mail = usuario.getString("email");
                String password = "12345";
                db.crearUsuario(name, mail, password);
                var--;
            }
        }
    }
}
