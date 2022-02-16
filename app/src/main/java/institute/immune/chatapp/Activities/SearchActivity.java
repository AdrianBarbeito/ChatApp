package institute.immune.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import institute.immune.chatapp.R;
import institute.immune.chatapp.Services.Bot;
import institute.immune.chatapp.Services.Bot2;
import institute.immune.chatapp.Services.Bot3;

public class SearchActivity extends AppCompatActivity {
    TextView politicBt, sportBt, movieBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bindings();
        setListeners();
    }

    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intentService = null;
            Intent intentActivity = new Intent(view.getContext(), ChatActivity.class) ;

            switch (view.getId()) {
                case R.id.politicTview:
                    intentService = new Intent(view.getContext(), Bot.class);
                    break;
                case R.id.sportsTview:
                    intentService = new Intent(view.getContext(), Bot2.class);
                    break;
                case R.id.moviesTview:
                    intentService = new Intent(view.getContext(), Bot3.class);
                    break;
            }
            startService(intentService);
            startActivity(intentActivity);
        }
    };

    private void bindings(){
        politicBt = findViewById(R.id.politicTview);
        sportBt = findViewById(R.id.sportsTview);
        movieBt = findViewById(R.id.moviesTview);
    }
    private void setListeners() {
        politicBt.setOnClickListener(listener);
        sportBt.setOnClickListener(listener);
        movieBt.setOnClickListener(listener);

        Bot.setupdateListener(this);

    }
}