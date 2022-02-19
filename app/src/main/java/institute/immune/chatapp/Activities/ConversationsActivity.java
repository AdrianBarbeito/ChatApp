package institute.immune.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import institute.immune.chatapp.R;

public class ConversationsActivity extends AppCompatActivity {
    private LinearLayout messageFrame;
    private View converView;
    private ImageButton searchCategoryBt, exitBt, profileBt;

    private int image, nickname, message;
    private int id = 0;
    private int buttonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);

        bindings();
        setListener();
    }

    private void bindings() {
        converView = getLayoutInflater().inflate(R.layout.fragment_conversation, null, false);
        messageFrame = findViewById(R.id.messageFrame);
        searchCategoryBt = findViewById(R.id.searchCategoryBt);
        exitBt = findViewById(R.id.exitMenu);
        profileBt = findViewById(R.id.profileMenu);
    }

    private void setListener() {
        searchCategoryBt.setOnClickListener(listenerMenu);
        exitBt.setOnClickListener(listenerMenu);
        profileBt.setOnClickListener(listenerMenu);
    }

    public void OnClickView(View view){
            addconver();
    }

    public View.OnClickListener listenerChat = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ChatActivity.class);
            intent.putExtra("idText", buttonId);
            startActivity(intent);
        }
    };

    public View.OnClickListener listenerMenu = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = null;
            switch (view.getId()){
                case R.id.searchCategoryBt:
                    intent = new Intent(view.getContext(), SearchActivity.class);
                    break;

                case R.id.exitMenu:
                    intent = new Intent(view.getContext(), AuthenticationActivity.class);
                    break;

                case R.id.profileMenu:
                    intent = new Intent(view.getContext(), ProfileActivity.class);
                    break;
            }
            startActivity(intent);
        }
    };

    public void addconver(){
        converView = getLayoutInflater().inflate(R.layout.fragment_conversation, null, false);
        messageFrame.addView(converView);
        buttonId = R.id.politicTview;
        converView.setId(buttonId);
        System.out.println(converView.getId());
        converView.setOnClickListener(listenerChat);
    }

    public int findId(){
        View v = findViewById(id);
        while (v != null){
            v = findViewById(++id);
        }
        return id++;
    }
}