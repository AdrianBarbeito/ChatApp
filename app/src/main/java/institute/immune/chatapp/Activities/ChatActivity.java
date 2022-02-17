package institute.immune.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import institute.immune.chatapp.R;
import institute.immune.chatapp.Services.Bot;

public class ChatActivity extends AppCompatActivity {
    private ImageView profileImage;
    private TextView nickNameTView;
    private ScrollView scrollChat;
    private EditText writeInput;
    private ImageButton sendBt;

    private Boolean firstMsg;
    private int ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        bindings();
        firstMsg = true;
        ids = -1;
        Bot.setupdateListener(this);
        Intent intentService = new Intent(this, Bot.class);
        startService(intentService);
    }

    private void bindings(){
        profileImage = findViewById(R.id.imageUserChat);
        nickNameTView = findViewById(R.id.nicknameChat);
        scrollChat = findViewById(R.id.scrollChat);
        writeInput = findViewById(R.id.msgEtext);
        sendBt = findViewById(R.id.sendBt);
    }

    public void setChat(String nickname) {
        nickNameTView.setText(nickname);
    }

    public void printMessage(String message, String position){
        TextView msg = new TextView(this);
        msg.setId(ids);
        msg.setText(message);
        msg.setBottom(scrollChat.getBottom());
        if (firstMsg){
            if (position.equalsIgnoreCase("right")){
                msg.setBottom(scrollChat.getBottom());
                msg.setRight(scrollChat.getRight());
            } else if (position.equalsIgnoreCase("left")){
                msg.setBottom(scrollChat.getBottom());
                msg.setLeft(scrollChat.getLeft());
            }
            scrollChat.addView(msg);
            ids--;
        } else {
            if (position.equalsIgnoreCase("right")){
                msg.constraintTop_toBottomOf(ids);
                msg.setRight(scrollChat.getRight());
                scrollChat.addView(msg);
            } else if (position.equalsIgnoreCase("left")){
                msg.constraintTop_toBottomOf(ids);
                msg.setLeft(scrollChat.getLeft());
                scrollChat.addView(msg);
            }
            scrollChat.addView(msg);
            ids--;
        }
    }
}
