package institute.immune.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import institute.immune.chatapp.Class.MyOpenHelper;
import institute.immune.chatapp.R;

public class ProfileActivity extends AppCompatActivity {
    private MyOpenHelper db;
    private EditText nickNameInput, mailInput;
    private TextView errorMessage, confirmText;
    private ImageButton confirmBt;
    private Integer idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bindings();
        confirmBt.setOnClickListener(listener);
    }

    private void bindings() {
        db = new MyOpenHelper(this);
        idUsuario = getIntent().getIntExtra("idUsuario", 0);
        nickNameInput = findViewById(R.id.nickNameProfile);
        mailInput = findViewById(R.id.mailProfile);
        errorMessage = findViewById(R.id.errorMessageProfile);
        confirmText = findViewById(R.id.confirmText);
        confirmBt = findViewById(R.id.confirmBtProfile);
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String nickName = nickNameInput.getText().toString();
            String mail = mailInput.getText().toString();
            if (!nickName.equalsIgnoreCase("") || !mail.equalsIgnoreCase("")){
                if (!nickName.equalsIgnoreCase("")){
                    db.replaceName(idUsuario, nickName);
                }

                if (!mail.equalsIgnoreCase("")){
                    db.replaceMail(idUsuario, mail);

                }
                confirmText.setText(R.string.modified);
                errorMessage.setText("");
            } else {
                errorMessage.setText(R.string.missingValues);
            }
        }
    };
}