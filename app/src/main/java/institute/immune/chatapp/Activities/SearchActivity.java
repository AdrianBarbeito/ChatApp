package institute.immune.chatapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import institute.immune.chatapp.R;

public class SearchActivity extends AppCompatActivity {
    private TextView politicBt, sportBt, movieBt;
   private ArrayList<Integer> conversId;
   private  ArrayList<String> conversCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        conversCategory= getIntent().getStringArrayListExtra("conversCategory");
        conversId = getIntent().getIntegerArrayListExtra("conversId");
        bindings();
        setListeners();
    }
    public View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), ConversationsActivity.class);
            TextView clicked = findViewById(view.getId());
            intent.putIntegerArrayListExtra("conversId", conversId);
            intent.putStringArrayListExtra("conversCategory", conversCategory);
            intent.putExtra("category",  clicked.getText());
            intent.putExtra("id", 0);
            startActivity(intent);
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
    }



}