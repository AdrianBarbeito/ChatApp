package institute.immune.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.zip.Inflater;

import institute.immune.chatapp.Fragments.ConversationFragment;
import institute.immune.chatapp.R;

public class ConversationsActivity extends AppCompatActivity {
    int image, nickname, message;
    LinearLayout messageFrame;
    View converView;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);
        messageFrame = findViewById(R.id.messageFrame);
    }
public void OnClickView(View view){
        addconver();
}
    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ChatActivity.class);
            intent.putExtra("idText", view.getId() );
            startActivity(intent);
        }
    };

        public void addconver(){
            converView = getLayoutInflater().inflate(R.layout.fragment_conversation, null, false);
            messageFrame.addView(converView);
            int buttonId = findId();
            converView.setId(buttonId);
            System.out.println(converView.getId());
            converView.setOnClickListener(listener);
        }

    public int findId(){
        View v = findViewById(id);
        while (v != null){
            v = findViewById(++id);
        }
        return id++;
    }
}