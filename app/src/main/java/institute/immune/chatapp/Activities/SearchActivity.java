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
            Intent intent = null;
            switch (view.getId()) {
                case R.id.politicTview:
                    intent = new Intent(view.getContext(), Bot.class);
                    break;
                case R.id.sportsTview:
                    intent = new Intent(view.getContext(), Bot2.class);
                    break;
                case R.id.moviesTview:
                    intent = new Intent(view.getContext(), Bot3.class);
                    break;
            }
            startService(intent);
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
        Bot2.setupdateListener(this);
        Bot3.setupdateListener(this);
    }
}