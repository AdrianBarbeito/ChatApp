package institute.immune.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
    private TextView accountQuestion, errorMessage, credentialsText;
    private EditText nickNameInput, mailInput, passwordInput;
    private Button switchToBt, apiBt, profileBt, searchBt, consultaBt;
    private ImageButton credentialsBt;
    private Integer idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        bindings();
        setListeners();
    }

    private void bindings() {
        db = new MyOpenHelper(this);
        accountQuestion = findViewById(R.id.accountQuestion);
        errorMessage = findViewById(R.id.errorMessage);
        nickNameInput = findViewById(R.id.nickNameInput);
        mailInput = findViewById(R.id.mailInput);
        passwordInput = findViewById(R.id.passwordInput);
        credentialsText = findViewById(R.id.credentialsText);
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
            String nickName = nickNameInput.getText().toString();
            String mail = mailInput.getText().toString();
            String password = passwordInput.getText().toString();
            if (credentialsText.getText().toString().equalsIgnoreCase("sign in")) {
                if (!nickName.equalsIgnoreCase("") && !mail.equalsIgnoreCase("") && !password.equalsIgnoreCase("")){
                    try {
                        db.crearUsuario(nickName, mail, password);
                        idUsuario = db.searchByMail(mail);
                        Intent intent = new Intent(view.getContext(), ConversationsActivity.class);
                        intent.putExtra("idUsuario", idUsuario);
                        startActivity(intent);
                    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                } else {
                    errorMessage.setText(R.string.missingValues);
                }

            } else if (credentialsText.getText().toString().equalsIgnoreCase("login")){
                try {
                    if (db.comprobarLogin(mail, password)){
                        idUsuario = db.searchByMail(mail);
                        Intent intent = new Intent(view.getContext(), ConversationsActivity.class);
                        intent.putExtra("idUsuario", idUsuario);
                        startActivity(intent);
                    } else {
                        errorMessage.setText(R.string.errorLogin);
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
            nickNameInput.setVisibility(nickNameInput.getVisibility() > 0 ? View.VISIBLE: View.INVISIBLE);
            if (switchToBt.getText().toString().equalsIgnoreCase("sign in")){
                credentialsText.setText(R.string.signIn);
                switchToBt.setText(R.string.login);
                accountQuestion.setText(R.string.alreadyAccount);
                errorMessage.clearComposingText();

            } else if (switchToBt.getText().toString().equalsIgnoreCase("login")){
                credentialsText.setText(R.string.login);
                switchToBt.setText(R.string.signIn);
                accountQuestion.setText(R.string.createAccount);
            }
            errorMessage.setText("");

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
