package institute.immune.chatapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import institute.immune.chatapp.R;
import institute.immune.chatapp.Services.Bot;
import institute.immune.chatapp.Services.Bot2;
import institute.immune.chatapp.Services.Bot3;

public class ChatActivity extends AppCompatActivity {
    private ImageView profileImage;
    private TextView nickNameTView, sendTView, receivedTView;;
    private EditText writeInput;
    private ImageButton sendBt;

    //Replicar converstions pero para mensajes
    LinearLayout messageFrame;
    View sentmessage, receivedmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        bindings();

        selectBot();
    }

public void selectBot(){
    Intent intentService = null;
    Integer id = getIntent().getIntExtra("idText", 0);

    switch (id) {
        case R.id.politicTview:
            intentService = new Intent(this, Bot.class);
            break;
        case R.id.sportsTview:
            intentService = new Intent(this, Bot2.class);
            break;
        case R.id.moviesTview:
            intentService = new Intent(this, Bot3.class);
            break;
    }
    startService(intentService);
}
    private void bindings(){
        profileImage = findViewById(R.id.imageUserChat);
        nickNameTView = findViewById(R.id.nicknameChat);
        writeInput = findViewById(R.id.msgEtext);
        sendBt = findViewById(R.id.sendBt);
        sendBt.setOnClickListener(messageListener);
        messageFrame = findViewById(R.id.chatLayout);
    }

    public void setChat(String nickname) {
        nickNameTView.setText(nickname);
    }
    public View.OnClickListener messageListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            addMessages();
        }
    };
    public void addMessages(){
        sentmessage = getLayoutInflater().inflate(R.layout.message_sent_layout, null, false);
        receivedmessage = getLayoutInflater().inflate(R.layout.message_received_layout, null, false);

        sendTView = sentmessage.findViewById(R.id.messagesent);
        receivedTView = receivedmessage.findViewById(R.id.messagereceived);

        sendTView.setText(writeInput.getText());
        receivedTView.setText("Adios");

        messageFrame.addView(sentmessage);
        messageFrame.addView(receivedmessage);

    }
}
