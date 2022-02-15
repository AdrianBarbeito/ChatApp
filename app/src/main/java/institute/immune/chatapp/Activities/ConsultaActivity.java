package institute.immune.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.ArrayList;

import institute.immune.chatapp.R;
import institute.immune.chatapp.Class.MyOpenHelper;
import institute.immune.chatapp.Class.User;

public class ConsultaActivity extends AppCompatActivity {
    private TextView listaUsuarios;
    private MyOpenHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        bindings();
        listaUsuarios.setMovementMethod(new ScrollingMovementMethod());
        showUsuarios(db.showUsuarios());
    }

    private void bindings() {
        db = new MyOpenHelper(this);
        listaUsuarios = findViewById(R.id.listaUsuarios);
    }


    private void showUsuarios(ArrayList<User> array){
        String usuarios = "";
        for (User user : array){
            usuarios += user.getId().toString() + " " + user.getName() + " " + user.getMail() + "\n";
        }
        listaUsuarios.setText(usuarios);
    }
}