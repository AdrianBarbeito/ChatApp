package institute.immune.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;

import institute.immune.chatapp.Class.MyOpenHelper;
import institute.immune.chatapp.R;

public class AuthenticationActivity extends AppCompatActivity {
    private MyOpenHelper db;
    private TextView accountQuestion, mensajeError;
    private EditText nickNameInput, mailInput, passwordInput;
    private Button credentialsBt, switchToBt;
    private Button consultaBt, apiBt, profileBt, searchBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        db = new MyOpenHelper(this);
        bindings();
        setListeners();
        db.RandomDb();
    }

    private void bindings() {
        accountQuestion = findViewById(R.id.accountQuestion);
        mensajeError = findViewById(R.id.mensajeError);
        nickNameInput = findViewById(R.id.nickNameInput);
        mailInput = findViewById(R.id.mailInput);
        passwordInput = findViewById(R.id.passwordInput);

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
            if (credentialsBt.getText().toString().equalsIgnoreCase("sign in")){
                try {
                    db.crearUsuario(nickNameInput.getText().toString(), mailInput.getText().toString(), passwordInput.getText().toString());
                    Intent intent = new Intent(view.getContext(), ConversationsActivity.class);
                    startActivity(intent);
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            } else if (credentialsBt.getText().toString().equalsIgnoreCase("login")){
                try {
                    if (db.comprobarLogin(mailInput.getText().toString(), passwordInput.getText().toString())){
                        Intent intent = new Intent(view.getContext(), ConversationsActivity.class);
                        startActivity(intent);
                    } else {
                        mensajeError.setText(R.string.errorLogin);
                    }
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public View.OnClickListener switchToListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (switchToBt.getText().toString().equalsIgnoreCase("sign in")){
                nickNameInput.setVisibility(View.VISIBLE);
                credentialsBt.setText(R.string.signIn);
                switchToBt.setText(R.string.login);
                accountQuestion.setText(R.string.alreadyAccount);
                mensajeError.clearComposingText();
                mensajeError.setText("");
            } else if (switchToBt.getText().toString().equalsIgnoreCase("login")){
                nickNameInput.setVisibility(View.INVISIBLE);
                credentialsBt.setText(R.string.login);
                switchToBt.setText(R.string.signIn);
                accountQuestion.setText(R.string.createAccount);
                mensajeError.setText("");
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
                    intent = new Intent(view.getContext(), ConversationsActivity.class);
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
                try {
                    db.crearUsuario(name, mail, password);
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                var--;
            }
        }
    }
}
