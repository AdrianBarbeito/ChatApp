package institute.immune.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import institute.immune.chatapp.R;

public class ChatActivity extends AppCompatActivity {
    private TextView nickTextView;
    private ImageView profileImage;
    private EditText write;
    private ImageButton send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        bindings();
    }
    public void bindings(){
    nickTextView = findViewById(R.id.nicknameChat);
    profileImage = findViewById(R.id.imageChat);
    write = findViewById(R.id.msgEtext);
    send  = findViewById(R.id.sendBt);
    }
}