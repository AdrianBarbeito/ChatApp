package institute.immune.chatapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

import institute.immune.chatapp.R;
import institute.immune.chatapp.Services.Bot;

public class ConversationsActivity extends AppCompatActivity {
    private LinearLayout messageFrame;
    private View converView;
    private ImageButton searchCategoryBt, exitBt, profileBt;
    TextView converNickName;
    TextView converText;
    private int image, nickname, message, id, ButtonId;
    private  ArrayList<Integer> conversId = new ArrayList<Integer>();
    private  ArrayList<String> conversCategory  = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);
        bindings();
        setListener();
        addConverToList();
        setConvers();
    }

    public void addconver(String category, int Id){
        converView = getLayoutInflater().inflate(R.layout.fragment_conversation, null, false);
        converNickName = converView.findViewById(R.id.converName);
        converText = converView.findViewById(R.id.converText);
        converNickName.setText(category);
        converView.setId(Id);
        converView.setContentDescription(category);
        messageFrame.addView(converView);
        converView.setOnClickListener(listenerChat);
    }

    public void addConverToList(){
        if (getIntent().getExtras()!= null){
            conversCategory= getIntent().getStringArrayListExtra("conversCategory");
            conversId = getIntent().getIntegerArrayListExtra("conversId");
            String category = getIntent().getStringExtra("category");
            Integer id = getIntent().getIntExtra("id", 0);
            conversCategory.add(category);
            conversId.add(id);

        }
    }

    private void setConvers() {
        for (int x = 0; x < conversCategory.size(); x++){
            addconver(conversCategory.get(x), conversId.get(x));
        }
    }


    private void bindings() {
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


    public View.OnClickListener listenerChat = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ChatActivity.class);
            converView = findViewById(view.getId());
            converNickName = view.findViewById(R.id.converName);
            intent.putExtra("id", converView.getId());
            intent.putExtra("category", converNickName.getText());
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
                    intent.putIntegerArrayListExtra("conversId", conversId);
                    intent.putStringArrayListExtra("conversCategory", conversCategory);
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

}